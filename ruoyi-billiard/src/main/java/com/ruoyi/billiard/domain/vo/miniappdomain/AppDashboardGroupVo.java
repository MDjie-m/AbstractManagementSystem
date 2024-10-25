package com.ruoyi.billiard.domain.vo.miniappdomain;

import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

@Data
public class AppDashboardGroupVo {

    private String title;

    private List<AppDashboardItemVo<?>> itemList= Lists.newArrayList();
}
