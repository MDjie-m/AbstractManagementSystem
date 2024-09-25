import Vue from "vue";

Vue.filter('timeFormat', function (value,format) {
  if (!value) {
    return '';
  }
  return  Vue.prototype.$time(value).format(format||'YYYY-MM-DD HH:mm:ss')
});
const WeekDays=['星期一','星期二','星期三','星期四','星期五','星期六','星期日']
Vue.filter('week', function (value) {
  if (!value) {
    return '';
  }
  return WeekDays[Vue.prototype.$time(value).day()]
});
