package com.ruoyi.billiard.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineUpItemVo  implements Serializable {

    private String num;

    private String createTime;
}
