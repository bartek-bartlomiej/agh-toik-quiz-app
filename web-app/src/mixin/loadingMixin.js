import client from '@/api'

export const loadingMixin = {
  data: function () {
    return {
      loading: false,
      externalData: undefined,
      operationName: undefined,
      shouldRetry: false,
      retryTime: 5000,
      consoleErrorMessage: 'Could not get data',
      toastErrorMessage: 'Could not get data'
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
    operationParams () {
      return null
    },
    operationData () {
      return null
    },
    secondsQuantity () {
      const seconds = Math.round(this.retryTime / 1000)
      return `${seconds} second${this.retryTime === 0 || seconds > 1 ? 's' : ''}`
    }
  },
  methods: {
    loadData () {
      this.loading = true
      this.operationMethod(this.operationParams, this.operationData)
        .then(response => {
          this.externalData = response.data
          this.loading = false
        })
        .catch(error => {
          console.error(`${this.consoleErrorMessage}: ${error.toString()}`)
          this.$buefy.toast.open({
            duration: 3000,
            message: `${this.toastErrorMessage}${this.shouldRetry ? `. Will try again in ${this.secondsQuantity}` : '…'}`,
            position: 'is-bottom',
            type: 'is-warning'
          })
          if (this.shouldRetry) {
            setTimeout(() => this.loadData(), this.retryTime)
          }
        })
    }
  }
}
