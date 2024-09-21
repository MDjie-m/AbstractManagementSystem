import Vue from "vue";

Vue.filter('numFormat', function (value,fractionDigits=2) {
  if (!value) {
    return value;
  }
  return  parseFloat (value).toFixed (fractionDigits)

});
