package com.ruoyi.common.core.page;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel
public class PageVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "总条数")
    private long total = 0;
    @ApiModelProperty(value = "数据集合")
    private List<T> rows;

    public PageVo() {

    }

   public PageVo(List<T> list){
       this.total = new PageInfo(list).getTotal();
       this.rows = list;
   }


    public PageVo(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
