package com.ruoyi.common.core.page;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResVo<T>   implements Serializable
    {
        private static final long serialVersionUID = 1L;

        /** 总记录数 */
        private long total;

        /** 列表数据 */
        private List<T> rows;

        /** 消息状态码 */
        private int code;

        /** 消息内容 */
        private String msg;

        public  static <T> PageResVo<T> success(List<T> list){
            PageResVo<T> res=new PageResVo<>();
            res.setCode(HttpStatus.SUCCESS);
            res.setRows(list);
            res.setTotal(new PageInfo<T>(list).getTotal());
            return  res;
        }
}
