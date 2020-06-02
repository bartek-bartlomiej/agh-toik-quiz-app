import client from '@/api'

export const loadingMixin = ({
  operationName,
  consoleError = 'Could not get data',
  toastMessage = 'Could not get data',
  shouldRetry = false,
  retryTime
}) => ({
  data: function () {
    return {
      loading: false,
      externalData: undefined,
      operationName: operationName,
      retryTime: shouldRetry ? (retryTime || 5000) : -1,
      consoleError: consoleError,
      toastMessage: toastMessage
    }
  },
  computed: {
    operationMethod () {
      if (typeof this.operationName !== 'string') {
        throw Error('Operation should be string')
      }
      if (typeof client[this.operationName] !== 'function') {
        throw Error(`Operation '${this.operationName}' not defined in API`)
      }
      return client[this.operationName]
    },
    shouldRetry () {
      return this.retryTime >= 0
    },
    secondsQuantity () {
      const seconds = Math.round(this.retryTime / 1000)
      return `${seconds} second${this.retryTime === 0 || seconds > 1 ? 's' : ''}`
    }
  },
  methods: {
    loadData (params = null, data = null, opts = null) {
      this.loading = true
      const operationMethod = this.operationMethod
      operationMethod(params, data, opts)
        .then(response => {
          this.externalData = response.data
          this.loading = false
        })
        .catch(error => {
          console.error(`${this.consoleError}: ${error.toString()}`)
          this.$buefy.toast.open({
            duration: 3000,
            message: `${this.toastMessage}${this.shouldRetry ? `. Will try again in ${this.secondsQuantity}` : '…'}`,
            position: 'is-bottom',
            type: 'is-warning'
          })
          if (this.shouldRetry) {
            setTimeout(() => this.loadData(), this.retryTime)
          }
        })
    }
  }
})
