package com.ruoyi.web.controller.view;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.web.controller.data.DataApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags ="浓度检测")
@RestController
@RequestMapping("/gas")
public class GasController {

    @ApiOperation("查询气体浓度")
    @GetMapping("/queryGasConcentration")
    public R<List<DataApiController.DeviceUploadData>> queryGasConcentration() {
        // TODO: query gas concentration from database
        List<DataApiController.DeviceUploadData> dataList = new ArrayList<>();
        return R.ok(dataList);
    }
}
