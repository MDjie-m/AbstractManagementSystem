package com.ruoyi.generator.domain.bo;

import com.ruoyi.generator.domain.GenTableColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author nuoqin
 * @DATA 2024/8/19 10:25
 */
@Setter
@Getter
@Builder
public class GenCustomBo {

    private List<Map<String,Object>> data;

    private List<GenTableColumn> columns;

    private Map<String,Object> singleData;

}
