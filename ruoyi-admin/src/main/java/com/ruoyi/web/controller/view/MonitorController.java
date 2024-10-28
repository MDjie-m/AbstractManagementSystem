package com.ruoyi.web.controller.view;

import com.ruoyi.common.core.domain.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
public class MonitorController {

    /**
     * 查询监测点历史/实时数据
     * @return
     */
    public R<String> queryMonitorData(String queryType){

        return R.ok("success");
    }

    /**
     * 查询过去24小时数据
     * @return
     */
    public R<String> queryPast24HoursData(){
        return R.ok("success");
    }

    /**
     * 查询监测点告警
     * @return
     */
    public R<String> monitorWarning(){
        return R.ok("success");
    }

    /**
     * 监控告警阈值设置
     * @return
     */
    public R<String> monitorWarningSet(){
        return R.ok("success");
    }

}
