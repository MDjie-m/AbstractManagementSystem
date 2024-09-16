package com.ruoyi.billiard.domain.vo;

import com.ruoyi.common.core.domain.model.KeyValueVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashierDeskDashboardResVo implements Serializable {


    private List<KeyValueVo<Integer,Long>> deskCount;

    private List<KeyValueVo<Integer,Long>> tutorCount;

}
