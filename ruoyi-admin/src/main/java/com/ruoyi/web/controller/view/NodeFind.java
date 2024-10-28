package com.ruoyi.web.controller.view;

import com.ruoyi.common.core.domain.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 节点寻优
 */
@RestController
@RequestMapping("/node")
public class NodeFind {

    /**
     * 固定节点寻优
     * @return
     */
    public R<String> fixedNodeFind(){
        return R.ok("固定节点寻优");
    }

    /**
     * 移动节点寻优
     * @return
     */
    public R<String> mobileNodeFind(){
        return R.ok("移动节点寻优");
    }

}
