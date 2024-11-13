<template>
  <div class="headerTop">
    <div class="iconBox">
      <img src="@/views/terminal/components/images/dxLogo.png" >
      <img src="@/views/terminal/components/images/tyLogo.png" >
    </div>
    <div class="title">山东物联网终端可视化管理(潍坊)</div>
    <div class="timeBox">
      <div class="timeTop">
        <span>{{currentTime}}</span>
      </div>
      <div class="timeBottom">
        <span>{{currentDay}}</span>
        <span>{{currentWeek}}</span>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Login",
  data() {
    return {
      activeKey: 'overview',
      now: new Date()
    }
  },
  computed: {
    currentDay() {
      return this.now.getFullYear() + '.' + (this.now.getMonth()+1) + '.' + this.now.getDate();
    },
    currentTime() {
      return this.padTime(this.now.getHours()) + ':' + this.padTime(this.now.getMinutes()) + ':' + this.padTime(this.now.getSeconds());
    },
    currentWeek() {
      const days = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
      return days[this.now.getDay()];
    },

  },
  created() {
    setInterval(() => {
      this.now = new Date();
    }, 1000);
  },
  methods: {
    padTime(time) {
      return time.toString().padStart(2, '0');
    },
    clickMenu(key) {
      this.activeKey = key;
      this.$router.push({
        path: '/terminal/'+key
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.headerTop {
  width: 100%;
  height: 100px;
  background: linear-gradient(rgb(2, 19, 35),rgba(2, 19, 35, 0.7) rgba(2, 19, 35, 0));
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  color: #fff;
  .title {
    font-size: 38px;
  }
  .iconBox {
    img {
      width: 130px;
      &:first-child {
        margin-right: 10px;
      }
    }
  }
  .timeBox {
    .timeTop {
      text-align: right;
      font-size: 22px;
    }
    .timeBottom{
      line-height: 20px;
      span:first-child {
        margin-right: 10px;
      }
    }
  }
}
// .terminal {
//   width: 100%;
//   height: 100%;
//   min-width: 1800px;
//   overflow-x: auto;
//   .headerTop {
//     background: linear-gradient(rgb(2, 19, 35),rgba(2, 19, 35, 0.7) rgba(2, 19, 35, 0));
//     display: flex;
//     justify-content: space-between;
//     position: relative;
//     z-index: 999;
//     .timeBox {
//       position: absolute;
//       top: 50%;
//       transform: translateY(-50%);
//       color: #fff;
//       margin-left: 15px;
//       display: flex;
//       align-items: center;
//       .timeLeft {
//         font-size: 24px;
//       }
//       .timeRight {
//         font-size: 12px;
//         line-height: 22px;
//         span {
//         display: block;
//         }
//       }
//       .line {
//         border: 1px solid rgba(255, 255, 255, 0.8);
//         margin: 0 6px;
//         height: 22px;
//       }
//       .tianqi {
//         width: 32px;
//         height: 32px;
//         margin-left: 6px;
//       }
//     }
//     .userBox {
//       position: absolute;
//       right: 15px;
//       top: 50%;
//       transform: translateY(-50%);
//       color: #fff;
//     }
//     .headerBox {
//       width: 100%;
//       height: 92px;
//       padding: 0 240px;
//       color: #fff;
//       background: url('~@/views/terminal/components/images/headerLineBg.png') center no-repeat;
//       background-position: center 6px;
//       display: flex;
//       justify-content: center;
//       .title {
//         width: 600px;
//         flex-shrink: 0;
//         text-align: center;
//         letter-spacing: 10px;
//         line-height: 57.2px;
//         font-weight: 600;
//         padding-top: 14px;
//         font-size: 44px;
//         background: url('~@/views/terminal/components/images/headerTitleBg.png') center no-repeat;
//         background-position: center 0px;
//       }
//       .headerLeft, .headerRight {
//         display: flex;
//         flex-grow: 1;
//         flex-basis: 0;
//         margin-top: 32px;
//         font-size: 18px;
//         .btn {
//           width: 142.5px;
//           height: 36px;
//           line-height: 36px;
//           cursor: pointer;
//           text-align: center;
//         }
//       }
//       .headerLeft {
//         .btn {
//           background: url('~@/views/terminal/components/images/btn.png') no-repeat;
//           background-size: 100% 100%;
//         }
//         .activeBtn {
//           background: url('~@/views/terminal/components/images/activeBtn.png') no-repeat;
//           background-size: 100% 100%;
//         }
//       }
//       .headerRight {
//         justify-content: end;
//         .btn {
//           background: url('~@/views/terminal/components/images/btn2.png') no-repeat;
//           background-size: 100% 100%;
//         }
//         .activeBtn {
//           background: url('~@/views/terminal/components/images/activeBtn2.png') no-repeat;
//           background-size: 100% 100%;
//         }
//       }
//     }
//   }
  
// }
</style>
