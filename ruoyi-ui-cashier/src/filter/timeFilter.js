import Vue from "vue";

Vue.filter('timeFormat', function (value,format) {
  if (!value) {
    return '';
  }
  return  Vue.prototype.$time(value).format(format||'YYYY-MM-DD HH:mm:ss')
});
