module.exports = {
  plugins: [
    '@babel/plugin-transform-optional-chaining',  //可选链 ?.
    '@babel/plugin-transform-nullish-coalescing-operator'  //空值合并 ??
  ],
  presets: [
    // https://github.com/vuejs/vue-cli/tree/master/packages/@vue/babel-preset-app
    '@vue/cli-plugin-babel/preset'
  ],
  'env': {
    'development': {
      // babel-plugin-dynamic-import-node plugin only does one thing by converting all import() to require().
      // This plugin can significantly increase the speed of hot updates, when you have a large number of pages.
      'plugins': ['dynamic-import-node']
    }
  }
}
