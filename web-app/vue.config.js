module.exports = {
  pages: {
    index: {
      entry: 'src/main.js',
      template: 'public/index.html',
      filename: 'index.html',
      title: 'Quiz - Web App'
    }
  },
  chainWebpack: config => {
    config.module
      .rule('yaml')
      .test(/\.ya?ml$/)
      .type('json')
      .use('yaml-loader')
      .loader('yaml-loader')
      .end()
  }
}
