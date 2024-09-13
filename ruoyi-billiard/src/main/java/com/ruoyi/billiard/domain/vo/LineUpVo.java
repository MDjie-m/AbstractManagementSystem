package com.ruoyi.billiard.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineUpVo  implements Serializable {

    private List<LineUpItemVo> numList= Lists.newArrayList();
    private Integer   CurrentNum=0;

}
