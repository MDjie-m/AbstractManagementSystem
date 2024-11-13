<template>
    <div class="diagnosis">
        <div class="searchBox">
            <img src="@/views/terminal/diagnosis/images/diagnosisIcon.png" >
            <el-input class="search-input-class" placeholder="请输入IMEI前14位数字" v-model="searchTxt" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');">
                <div class="searchBtn" slot="append" @click="searchFun">一键诊断</div>
            </el-input>
        </div>
        <div class="content">
           
            <div class="imgItem">
                <div class="imgBox" :class="netWorkObj ? 'successColor' : ''"><img src="@/views/terminal/diagnosis/images/zduan.svg" /></div>
                <div class="info">终端正常</div>
            </div>
            <div class="arrowIcon"><img src="@/views/terminal/diagnosis/images/diagnosis_arrow.svg" /></div>
            <div class="imgItem">
                <div class="imgBox" :class="netWorkObj ? ((netWorkObj.codeStat == '345' || netWorkObj.codeStat == '346') ? 'errorColor': 'successColor') : ''"><img src="@/views/terminal/diagnosis/images/yewu.svg" /></div>
                <div class="info" v-if="netWorkObj&&(netWorkObj.codeStat == '345' || netWorkObj.codeStat == '346')">{{ netWorkObj.codeInfo }}</div>
                <div class="info" v-else>业务正常</div>
            </div>
            <div class="arrowIcon"><img src="@/views/terminal/diagnosis/images/diagnosis_arrow.svg" /></div>
            <div class="imgItem">
                <div class="imgBox" :class="netWorkObj ? (netWorkObj.codeStat == '347' ? 'errorColor' : 'successColor') : ''"><img src="@/views/terminal/diagnosis/images/network.svg" /></div>
                <div class="info" v-if="netWorkObj&&netWorkObj.codeStat == '347'">{{ netWorkObj.codeInfo }}</div>
                <div class="info" v-else>网络正常</div>
            </div>
            <div class="arrowIcon"><img src="@/views/terminal/diagnosis/images/diagnosis_arrow.svg" /></div>
            <div class="imgItem">
                <div class="imgBox" :class="netWorkObj ? (netWorkObj&&netWorkObj.codeStat != '200' ? 'errorColor' :  'successColor') : ''"><img src="@/views/terminal/diagnosis/images/plat.svg" /></div>
                <div class="info"  v-if="netWorkObj&&netWorkObj.codeStat != '200'" >南向上报异常</div>
                <div class="info" v-else>平台正常</div>
            </div>
        </div>
    </div>
</template>
<script>
import { cardNetStopStateQuery } from "@/api/diagnosis/index.js";
export default {
    data() {
        return {
            searchTxt: '',
            netWorkObj: null,
            // codeStat: '345', // 346       347
            // msg: '卡停机' //异常断网   3天内无详单
        }
    },
    mounted() {
        // this.netWorkObj = {
        //     codeStat: '200',
        //     codeInfo: 'ok',
        // }
        this.searchFun()
    },
    methods: {
        searchFun() {
            // cardNetStopStateQuery({
            //     imei: this.searchTxt
            // }).then(res=> {
            //     if (res.code == 200) {
            //         this.netWorkObj = res.data;
            //     }
            // })
            setTimeout(() => {
                this.netWorkObj = {
                    // 345: 卡停机  346: 异常断网   347: 3天内无详单
                    codeStat: '345',
                    codeInfo: '卡停机',
                    // codeStat: '346',
                    // codeInfo: '异常断网',
                    // codeStat: '347',
                    // codeInfo: '3天内无详单',
                    // codeStat: '200',
                    // codeInfo: 'ok',
                }
            }, 2000);
        }
    }
}
</script>
<style lang="scss" scoped>
.diagnosis {
    padding-top: 100px;
    height: calc(100vh - 84px);
    .searchBox {
        display: flex;
        justify-content: center;
        align-items: center;
        img {
            width: 250px;
        }
        .search-input-class {
            width: 800px;
            height: 60px;
            margin-left: 80px;
            margin-top: 30px;
            ::v-deep {
                .el-input__inner {
                    height: 100%;
                    font-size: 24px;
                }
                .el-input-group__append {
                    width: 200px;
                    background: #007AFF;
                    color: #ffffff;
                    font-size: 24px;
                    border: 0;
                    cursor: pointer;
                    .searchBtn {
                        width: 100%;
                        height: 60px;
                        line-height: 60px;
                        text-align: center;
                    }
                }
            }
        }
    }
    .content {
        display: flex;
        justify-content: center;
        padding: 10% 2% 0;
        .imgItem {
            position: relative;
            .imgBox {
                width: 150px;
                height: 150px;
                margin-bottom: 20px;
                border-radius: 75px;
                background: #cccccc;
                display: flex;
                justify-content: center;
                align-items: center;
                img {
                    width: 80px;
                    // height: 100%;
                }
            }
            .errorColor {
                background: #FB5967;
            }
            .successColor {
                background: #18D85F;
            }
            .info {
                text-align: center;
                font-size: 18px;
                line-height: 28px;
                color: #575757;
            }
        }
        .arrowIcon {
            padding: 50px 20px 0;
            img {
                width: calc(15vh);
            }
        }
    }
}
</style>