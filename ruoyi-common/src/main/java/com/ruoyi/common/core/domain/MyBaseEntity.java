package com.ruoyi.common.core.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MyBaseEntity extends BaseEntity {
    /** 创建者 */
    @TableField("create_by_id")
    private Long createById;

    /** 创建者 */
    @TableField("update_by_id")
    private Long updateById;
}
