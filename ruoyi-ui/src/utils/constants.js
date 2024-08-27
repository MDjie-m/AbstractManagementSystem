export const page = {
  limit: [20, 40, 60, 80, 100],
  page: 1,
  layout: 'total, sizes, prev, pager, next, jumper'
}

/**
 * 无限极分类type
 * @type {{product: number, attachment: number, menu: number, article: number, operator: number}}
 */
export const categoryType = [ // 1 产品分类，2 附件分类，3 文章分类， 4 设置分类， 5 菜单分类， 6 配置分类， 7 秒杀配置
  { name: '产品分类', value: 1, shortName: '产品' },
  { name: '附件分类', value: 2, shortName: '附件' },
  { name: '文章分类', value: 3, shortName: '文章' },
  { name: '设置分类', value: 4, shortName: '设置' },
  { name: '菜单分类', value: 5, shortName: '菜单' },
  { name: '配置分类', value: 6, shortName: '配置' },
  { name: '秒杀配置', value: 7, shortName: '秒杀' }
]

export const picModules = ['course','gauge','zx']

export const picTypes = ['class','banner','item','section','avatar','other','column','team','questions','company']

export const wList = [
  { label: '上班', value: '0' },
  { label: '休息', value: '1' }
]

export const limitStatus = [
  { label: '不限制', value: 0 },
  { label: '限制', value: 1 }
]

export const modeList = [
  { label: '全部', value: '' },
 // { label: '语音咨询', value: 1 },
  { label: '视频咨询', value: 2 },
 // { label: '当面咨询', value: 3 },
]

//咨询师级别
export const levelList = [
  { label: '学员咨询师', value: 1 },
  { label: '初级咨询师', value: 2 },
  { label: '中级咨询师', value: 3 },
  { label: '高级咨询师', value: 4 },
  { label: '督导师', value: 5 },
]

export const serviceObjectList = [
  { label: '来访者', value: "1" },
  { label: '个人督导', value: "2" },
  { label: '个人体验', value: "3" },
]

export const typeList = [
  { label: '全部', value: '' },
  { label: '单次', value: 1 },
  { label: '套餐', value: 2 }
]

export const comListStatus = [
  { label: '全部', value: '' },
  { label: '启用', value: '0' },
  { label: '禁用', value: '1' }
]

// 0-草稿,1-审核中,2-审核通过-已开通账号,3-审核通过-未开通账号,4-审核驳回
export const partnerStatus = [
  { label: '全部', value: '' },
  { label: '草稿', value: '0' },
  { label: '审核中', value: '1' },
  { label: '审核通过-已开通账号', value: '2' },
  { label: '审核通过-未开通账号', value: '3' },
  { label: '审核驳回', value: '4' },
]

export const partnerTypes = [
  { label: '合作型', value: '1' },
  // { label: '签约型（团体督导）', value: '2' },
  // { label: '签约型（个体督导）', value: '3' },
  { label: '签约型', value: '4' },
]

// 1-待签署,2-已撤销,3-逾期未签署,4-待生效,5-生效中,6-已失效,7-终止 */
export const contractStatus = [
  { label: '全部', value: '' },
  { label: '待签署', value: '1' },
  { label: '已撤销', value: '2' },
  { label: '逾期未签署', value: '3' },
  { label: '待生效', value: '4' },
  { label: '生效中', value: '5' },
  { label: '已失效', value: '6' },
  { label: '终止', value: '7' },
]

export const roleListStatus = [
  { label: '全部', value: '' },
  { label: '显示', value: 1 },
  { label: '不显示', value: 0 }
]

export const showHiddenStatus = [
  { label: '显示', value: '‘1’' },
  { label: '不显示', value: '‘0’' }
]

export const switchStatus = [
  { label: '开启', value: 1 },
  { label: '关闭', value: 0 }
]

export const deletedOrNormal = [
  { label: '正常', value: 0 },
  { label: '已删除', value: 1 }
]

export const orderStatus = [
  { label: '全部', value: '' },
  { label: '待付款', value: '0' },
  { label: '进行中', value: '1' },
  { label: '已完成', value: '2' },
  { label: '已取消', value: '3' },
  { label: '已关闭', value: '4' },
]

export const payStatus = [
  { label: '全部', value: '' },
  { label: '未支付', value: '1' },
  { label: '支付成功', value: '2' },
  { label: '退款中', value: '3' },
  { label: '部分退', value: '4' },
  { label: '全单退', value: '5' },
  { label: '退款失败', value: '6' },
]

export const logName = [
  { label: '创建', value: 'create' },
  { label: '支付', value: 'pay' },
  { label: '改价', value: 'price' },
  { label: '核销', value: 'hx' },
  { label: '备注', value: 'remark' },
  { label: '完成', value: 'finished' },
  { label: '退款', value: 'refund' },
  { label: '转介', value: 'change' },
  { label: '取消', value: 'cancel' },
  { label: '关闭', value: 'close' },
]

export const gaugeCompute = [
  { label: '分数累加', value: 1 },
  { label: '多维累加', value: 2 },
  { label: 'MBTI', value: 3 },
  { label: '分数比率换算', value: 4 },
  // { label: 'SAS', value: 5 },
  // { label: 'SCL-90', value: 6 },
  // { label: '儿童智力', value: 7 },
  { label: '瑞文IQ', value: 8 },
  // { label: '马斯洛安全感', value: 6 },
]

export const gaugeMbti = [
  { label: 'E', value: 'E' },
  { label: 'I', value: 'I' },
  { label: 'S', value: 'S' },
  { label: 'N', value: 'N' },
  { label: 'T', value: 'T' },
  { label: 'F', value: 'F' },
  { label: 'J', value: 'J' },
  { label: 'P', value: 'P' },
  { label: '章节', value: 'O' },
]

export const gaugeMbtiRes = [
  { label: 'ISTJ', value: 'ISTJ' },
  { label: 'ISFJ', value: 'ISFJ' },
  { label: 'INFJ', value: 'INFJ' },
  { label: 'INTJ', value: 'INTJ' },
  { label: 'ISTP', value: 'ISTP' },
  { label: 'ISFP', value: 'ISFP' },
  { label: 'INFP', value: 'INFP' },
  { label: 'INTP', value: 'INTP' },
  { label: 'ESTP', value: 'ESTP' },
  { label: 'ESFP', value: 'ESFP' },
  { label: 'ENFP', value: 'ENFP' },
  { label: 'ENTP', value: 'ENTP' },
  { label: 'ESTJ', value: 'ESTJ' },
  { label: 'ESFJ', value: 'ESFJ' },
  { label: 'ENFJ', value: 'ENFJ' },
  { label: 'ENTJ', value: 'ENTJ' },
]

/**
 * 暂时弃用
 * @type {*[]}
 */
export const configCategory = [
  { label: '系统', value: '0' },
  { label: '应用', value: '1' },
  { label: '支付', value: '2' },
  { label: '其他', value: '3' }
]

/**
 * 表单配置集合集中配置
 * @type {{id: number, dis: string}[]}
 */
export const formConfigIds = [
  { id: 84, dis: '微信公众号表单配置' },
  { id: 86, dis: '秒杀配置' }
]

/**
 * 时间选择器
 */
export const fromList = {
  title: '选择时间',
  custom: true,
  fromTxt: [
    { text: '全部', val: '' },
    { text: '今天', val: 'today' },
    { text: '昨天', val: 'yesterday' },
    { text: '最近7天', val: 'lately7' },
    { text: '最近30天', val: 'lately30' },
    { text: '本月', val: 'month' },
    { text: '本年', val: 'year' }
  ]
}

// 统计管理时间选择器
export const timeList = {
  title: '选择时间',
  custom: true,
  fromTxt: [
    { text: '昨天', val: `` },
    { text: '最近7天', val: 'lately7' },
    { text: '最近30天', val: 'lately30' },
  ]
}

// 咨询类型
export const consultType = [
  { label: '咨询', value: '1' },
  { label: '倾听', value: '2' },
  { label: '督导', value: '3' },
]


//督导类型
export const supervisionType = [
  { label: '团体督导', value: '1' },
  { label: '个体督导', value: '2' },
  { label: '个人体验', value: '3' },
]

// 星期
export const weekDay = [
  { label: '星期一', value: '1' },
  { label: '星期二', value: '2' },
  { label: '星期三', value: '3' },
  { label: '星期四', value: '4' },
  { label: '星期五', value: '5' },
  { label: '星期六', value: '6' },
  { label: '星期日', value: '7' },
];

// 服务类型
export const serverType = [
  { label: '团队督导', value: '1' },
  { label: '个人督导', value: '2' },
  { label: '个人体验', value: '3' },
  { label: '课程', value: '4' },
  { label: '成长套餐', value: '5' },
];

// 支付方式
export const payType = [
  { label: '现款支付', value: '1' },
  { label: '权益支付', value: '2' },
];

// 用户类型
export const userType = [
  { label: '来访者用户', value: 1 },
  { label: '咨询师用户', value: 2 },
];

// 数据类型
export const dataType = [
  { label: '团队督导', value: 'teamSup' },
  { label: '咨询师信息', value: 'consultantInfo' },
  { label: '套餐', value: 'package' },
  { label: '课程', value: 'course' },
  { label: '测评', value: 'gauge' },
];
