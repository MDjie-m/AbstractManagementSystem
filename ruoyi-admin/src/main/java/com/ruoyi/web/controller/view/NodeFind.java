package com.ruoyi.web.controller.view;

import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 节点寻优
 */
@Api("节点寻优")
@RestController
@RequestMapping("/node")
public class NodeFind {

    /**
     * 固定节点寻优
     * @return
     */
    @ApiOperation("固定节点寻优")
    @GetMapping("/fixedNodeFind")
    public R<String> fixedNodeFind(@ApiParam(value = "排点ID", required = true) String emiId){
        return R.ok("固定节点寻优");
    }

    /**
     * 移动节点寻优
     * @return
     */
    @ApiOperation("移动节点寻优")
    @GetMapping("/mobileNodeFind")
    public R<String> mobileNodeFind(@ApiParam(value = "排点ID", required = true) String emiId){
        return R.ok("移动节点寻优");
    }

}
