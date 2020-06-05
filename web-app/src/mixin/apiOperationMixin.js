import client from '@/api'

export default {
  data: function () {
    return {
      pending: false,
      operationName: undefined,
      shouldShowSuccessToast: false,
      shouldShowErrorToast: true,
      shouldRetry: false,
      retryTime: 5000,
      consoleErrorMessage: 'Operation failed',
      toastSuccessMessage: 'Operation successful',
      toastErrorMessage: 'Operation failed'
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
    }
  },
  methods: {
    performOperation () {
      this.pending = true
      this.operationMethod(this.operationParams, this.operationData)
        .then(response => {
          if (this.shouldShowSuccessToast) {
            this.showToast('success')
          }
          this.handleOperationSucceeded(response.data)
          this.pending = false
        })
        .catch(error => {
          console.error(`${this.consoleErrorMessage}: ${error.toString()}`)
          if (this.shouldShowErrorToast) {
            this.showToast('error')
          }
          if (this.shouldRetry) {
            setTimeout(() => this.performOperation(), this.retryTime)
            return
          }
          this.pending = false
        })
    },
    showToast (type) {
      const data = {
        success: () => ({
          message: this.toastSuccessMessage,
          type: 'is-success'
        }),
        error: () => {
          const seconds = Math.round(this.retryTime / 1000)
          const secondsQuantity = `${seconds} second${this.retryTime === 0 || seconds > 1 ? 's' : ''}`
          return {
            message: `${this.toastErrorMessage}${this.shouldRetry ? `. Will try again in ${secondsQuantity}` : ''}`,
            type: 'is-warning'
          }
        }
      }[type]()
      this.$buefy.toast.open({
        duration: 3000,
        position: 'is-bottom',
        message: data.message,
        type: data.type
      })
    },
    handleOperationSucceeded (data) {}
  }
}
