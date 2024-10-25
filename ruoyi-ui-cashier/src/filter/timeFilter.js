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
Vue.filter('hhmm', function (value) {
  if (!value) {
    return '';
  }
  const duration = Vue.prototype.$time.duration(value,'minutes');
  const hours = duration.hours();
  const minutesLeft = duration.minutes();
  if(hours>0){
    return  `${hours}小时${minutesLeft}分`
  }
  return  `${minutesLeft}分钟`
});
