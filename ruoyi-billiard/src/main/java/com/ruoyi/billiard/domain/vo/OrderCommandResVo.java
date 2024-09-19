package com.ruoyi.billiard.domain.vo;

import com.ruoyi.billiard.domain.BaseFee;
import com.ruoyi.billiard.domain.Order;
import com.ruoyi.billiard.domain.StoreDesk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCommandResVo {
     private List<StoreDesk> busyDesks;
}
