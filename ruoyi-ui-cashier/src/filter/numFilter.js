import Vue from "vue";

Vue.filter('numFormat', function (value,fractionDigits=2) {
  if (!value) {
    return value;
  }
  return  parseFloat (value).toFixed (fractionDigits)

});
Vue.filter('numPad', function (value,len=2,prefix="0") {
  if (value===null ||value ===undefined) {
    return value;
  }
  return  String(value).padStart(len||2,prefix||'0')

});
