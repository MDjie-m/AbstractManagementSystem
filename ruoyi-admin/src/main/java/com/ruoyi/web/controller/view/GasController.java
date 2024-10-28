package com.ruoyi.web.controller.view;

import com.ruoyi.common.core.domain.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gas")
public class GasController {

    public R<String> queryGasConcentration() {
        // TODO: query gas concentration from database
        return R.ok("100");
    }
}
