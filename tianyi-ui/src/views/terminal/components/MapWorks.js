//天地图key
const key = '011aa16e96f4b28f953f5d48f2e18e28'
import * as Cesium from 'cesium'
 
// 初始视图定位在中国
Cesium.Camera.DEFAULT_VIEW_RECTANGLE = Cesium.Rectangle.fromDegrees(119.142584,36.712430, 15000);
// 初始化地图
function initMap(container) {
  let viewer = new Cesium.Viewer(container, {
    animation: false, // 是否显示动画控件
    baseLayerPicker: false, // 将图层选择的控件关掉，才能添加其他影像数据
    fullscreenButton: false,//是否显示全屏按钮
    geocoder: false, // 是否显示地名查找控件
    homeButton: false,//是否显示Home按钮
    infoBox: false, // 是否显示点击要素之后显示的信息
    sceneModePicker: false,//是否显示3D/2D选择器
    selectionIndicator: false, //是否显示选取指示器组件
    timeline: false, // 是否显示时间线控件
    navigationHelpButton: false, // 是否显示帮助信息控件
    scene3DOnly: true, //如果设置为true，则所有几何图形以3D模式绘制以节约GPU资源
    // orderIndependentTranslucency: true, //半透明
    // contextOptions: {
    //   webgl: {
    //     alpha: true //线路白色边
    //   }
    // }
  })
  // 去除logo
  viewer._cesiumWidget._creditContainer.style.display = 'none';
  // 设置视图等级范围
  viewer.scene.screenSpaceCameraController.maximumZoomDistance =zoomToAltitude(viewer, 10);
  viewer.scene.screenSpaceCameraController.minimumZoomDistance = zoomToAltitude(viewer, 17);
  // 抗锯齿
  viewer.scene.fxaa = false;
  viewer.scene.postProcessStages.fxaa.enabled = false;
  // 将原来鼠标中键倾斜视图修改为鼠标右键触发
  viewer.scene.screenSpaceCameraController.tiltEventTypes = [Cesium.CameraEventType.RIGHT_DRAG]
  // 将原来鼠标右键放大缩放修改为鼠标滚轮滚动
  viewer.scene.screenSpaceCameraController.zoomEventTypes = [Cesium.CameraEventType.WHEEL]
  if (Cesium.FeatureDetection.supportsImageRenderingPixelated()) {
    // 判断是否支持图像渲染像素化处理
    viewer.resolutionScale = window.devicePixelRatio
  }
  // 移除默认影像
  removeAll(viewer)
  // 地形深度测试
  viewer.scene.globe.depthTestAgainstTerrain = false
  // 背景透明
  viewer.scene.globe.baseColor = new Cesium.Color(0.0, 0.0, 0.0, 0);
  return viewer
}
// 增加标注图层
function addTdtLayer(viewer, options) {
  let url = `https://t{s}.tianditu.gov.cn/DataServer?T=${options.type}&x={x}&y={y}&l={z}&tk=${key}`
  const layerProvider = new Cesium.UrlTemplateImageryProvider({
    url: url,
    subdomains: ['0','1','2','3','4','5','6','7'],
    tilingScheme: new Cesium.WebMercatorTilingScheme(),
    maximumLevel: 18
  });
  viewer.imageryLayers.addImageryProvider(layerProvider);
}
// 增加带坐标系的标注图层
function addTdtLayer2(viewer, options) {
  let url = `https://t{s}.tianditu.gov.cn/${options.type}/wmts?tk=${key}`
  const layerProvider = new Cesium.WebMapTileServiceImageryProvider({
    url: url,
    layer: options.type.slice(0,options.type.length - 2),
    style: 'default',
    format: 'tiles',
    tileMatrixSetID: "w",
    tilingScheme: new Cesium.WebMercatorTilingScheme(),
    subdomains: ['0','1','2','3','4','5','6','7'],
    maximumLevel: 18
  });
  viewer.imageryLayers.addImageryProvider(layerProvider);
}
// 设置地图类型
function changeBaseMap(viewer, type) {
  removeAll(viewer)
  switch(type) {
    case 0: //影像地图
      addTdtLayer(viewer, {
        type: 'img_w'
      })
      addTdtLayer(viewer, {
        type: 'cia_w'
      })
      break
    case 1: //电子地图
      addTdtLayer(viewer,{
        type: 'vec_w'
      })
      addTdtLayer(viewer,{
        type: 'cva_w'
      })
      break
    case 2: //地形图
      addTdtLayer(viewer,{
        type: 'ter_w'
      })
      addTdtLayer(viewer, {
        type: 'cta_w'
      })
      break
  }
}
// 销毁地图图层
function removeAll(viewer) {
  viewer.imageryLayers.removeAll();
}
// 销毁点线图层
function removePointerAll(viewer) {
  viewer.entities.removeAll();
}
// 销毁地图实例
function destroy(viewer) {
  viewer.entities.removeAll();
  viewer.imageryLayers.removeAll();
  viewer.destroy();
}
// 设置视图位置和方向
function setView(viewer, coords,hpr) {
  viewer.camera.setView({
    destination :Cesium.Cartesian3.fromDegrees(coords[0], coords[1], coords[2]),//摄像机在 WGS84（世界）坐标中的最终位置，或从自上而下视图可见的矩形
    orientation : {//包含方向和向上属性或航向、俯仰和滚动属性的对象
      heading : Cesium.Math.toRadians(hpr.heading), // （方向）east, default value is 0.0 (north)此处可根据个人需求实际取值
      pitch : Cesium.Math.toRadians(hpr.pitch),    // （视角）default value (looking down)此处可根据个人需求实际取值
      roll : Cesium.Math.toRadians(hpr.roll),      //（倾斜角度） default value此处可根据个人需求实际取值
    }
  });
}
// 设置标注点
function setPointLayer(viewer, point) {
  let pointStyle = {};
  if (point.terminalCnt >= 75) {
    pointStyle = { pixelSize: 100, color:  'rgb(134,0,47)', font: 30 };
  } else if (point.terminalCnt >= 50) {
    pointStyle = { pixelSize: 80, color:  'rgb(224,0,22)', font: 24 };
  } else if (point.terminalCnt >= 25) {
    pointStyle = { pixelSize: 60, color:  'rgb(255,228,0)', font: 20 };
  } else {
    pointStyle = { pixelSize: 40, color:  'rgb(14,229,0)', font: 18 };
  }
  viewer.entities.add({
    position: Cesium.Cartesian3.fromDegrees(point.lng, point.lat, 1), //实体的定位
    id: point.bsId || new Date().getTime() +'default_id',
    point: {
      pixelSize: pointStyle.pixelSize, //圆的大小
      color: Cesium.Color.fromCssColorString(pointStyle.color), //圆的颜色
    },
    label: {
      text: point.terminalCnt+'',
      scale:0.5,
      font: (pointStyle.font * 2) + 'px Helvetica, sans-serif',
      fillColor: Cesium.Color.fromCssColorString('rgb(0,0,0)'),
      eyeOffset: new Cesium.Cartesian3(0.0, 0.0, 0.0),
      disableDepthTestDistance: Number.POSITIVE_INFINITY,
    },
    myInfo: point,
  });
}
// 设置连线
function setLineLayer(viewer, prev, curr) {
  var startPosition = Cesium.Cartesian3.fromDegrees(prev.lng, prev.lat,2);
  var endPosition = Cesium.Cartesian3.fromDegrees(curr.lng, curr.lat,2);
  viewer.entities.add({
    polyline: {
      positions: [startPosition, endPosition], // 定义线的位置数组
      width: 10,
      zIndex: 1,
      material: new Cesium.PolylineArrowMaterialProperty(Cesium.Color.fromCssColorString('#F59A23')), // 线的颜色
      // depthFailMaterial: Cesium.Color.BLUE  #F59A23
    }
});
}
//点击地图事件
function addLeftClick(viewer, callback) {
  const handler = new Cesium.ScreenSpaceEventHandler(viewer.scene.canvas);
  handler.setInputAction((event) => {
    const pick = viewer.scene.pick(event.position);
    //选中某模型pick选中的对象
    if (pick && pick.id) {
      let entityID = pick.id._id;
      let myEntity = viewer.entities.getById(entityID);
      callback(myEntity.myInfo);
    } else {
      callback(null);
    }
  }, Cesium.ScreenSpaceEventType.LEFT_CLICK)
}
// 修改相机高度事件
function setScaleMap(viewer, callback) {
  viewer.scene.camera.moveEnd.addEventListener(() => {
    //获取当前相机高度
    let height = Math.ceil(viewer.camera.positionCartographic.height);
    // let level = getHeightByLevel(height); //等级
    let cords = fourWind(viewer)
    callback(cords[0], cords[1])
  })
}
// 获取当前视图范围
function fourWind(viewer) {
  var extent = viewer.camera.computeViewRectangle();
  // 提取四个角的经纬度
  var northwest = Cesium.Rectangle.northwest(extent); //西北
  var northeast = Cesium.Rectangle.northeast(extent); //东北
  var southwest = Cesium.Rectangle.southwest(extent); //西南
  var southeast = Cesium.Rectangle.southeast(extent); //东南
  return [[northwest.longitude, northwest.latitude], [southeast.longitude, southeast.latitude]]
}
// 根据高度获得等级
function getHeightByLevel(viewer, level) {
  var A = 40487.57;
  var B = 0.00007096758;
  var C = 91610.74;
  var D = -40467.74;
  return Math.round(D + (A - D) / (1 + Math.pow(level / C, B)));
};
// 根据等级获得高度
function zoomToAltitude(viewer, zoom) {
  var A = 40487.57;
  var B = 0.00007096758;
  var C = 91610.74;
  var D = -40467.74;
  return Math.round(C * Math.pow((A - D) / (zoom - D) - 1, 1 / B));
}
 
export {
  initMap,
  changeBaseMap,
  setView,
  removeAll,
  removePointerAll,
  destroy,
  setPointLayer,
  addLeftClick,
  setScaleMap,
  zoomToAltitude,
  setLineLayer
}
 