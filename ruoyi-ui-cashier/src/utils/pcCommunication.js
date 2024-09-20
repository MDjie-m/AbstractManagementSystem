import Vue from "vue";

const PcCallMethods = {};
window.PcCallMethods = PcCallMethods;
export const DeviceMethodNames = {
  LightSwitch: 'light.switch',
  LightStateQuery: 'light.state.query.all',
  Speech: "speech"
}

export function registerMethod(methodName, func) {
  if (PcCallMethods[methodName]) {
    Vue.prototype.$modal.msgWarning("方法名重复，无法注册");
    return false;
  }
  PcCallMethods[methodName] = func;
  return true;

}

export function removeMethod(methodName) {
  delete PcCallMethods[methodName]
}

function getUuid() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    var r = (Math.random() * 16) | 0,
      v = c == 'x' ? r : (r & 0x3) | 0x8;
    let res = v.toString(16).toString();
    return res;
  }).replace(/-/ig, "");
}

export function callPCMethod(type, data, timeout = 5000) {
  console.log("pc调用参数：",type,JSON.stringify(data))

  let msgId = getUuid();
  let res = window.DeviceMethod?.callMethd(type, (typeof (data) === "string") ? data : JSON.stringify(data), msgId);
  let timeId = null;
  return new Promise(resolve => {
    registerMethod(type + msgId, (res) => {
      if (timeId) {
        clearImmediate(timeId)
      }
      resolve(res)
    })
  }, reject => {
    timeId = setTimeout(()=>{
      throw  new Error(`${type}:${msgId},调用超时`)
    }, timeout || 5000)
  }).finally(() => {
    removeMethod(type + msgId)
  })
}

window.onPCCall = function (type, msg) {
  let failRes = {code: 500, msg: `未找到[${type}]方法`};
  if (!PcCallMethods[type]) {
    console.log("调用失败" + JSON.stringify(failRes))
    return JSON.stringify(failRes);
  }
  return PcCallMethods[type](JSON.parse(msg))
}
