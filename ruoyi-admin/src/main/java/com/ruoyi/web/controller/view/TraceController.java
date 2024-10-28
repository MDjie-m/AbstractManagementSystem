package com.ruoyi.web.controller.view;

import com.ruoyi.common.core.domain.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 溯源
 */
@RestController
@RequestMapping("/trace")
public class TraceController {

    /**
     * 溯源点溯源分析结果
     * @return
     */
    public R<String> trace(){
        return R.ok("trace");
    }
}
