package com.ruoyi.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限过滤注解
 *
 * 数据权限信息是存放在role中的，
 * 
 * @author ruoyi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 部门表的别名，用来给填充{}.dept_id字段
     */
    public String deptAlias() default "";

    /**
     * 用户表的别名，用来填充(}.user_id字段
     */
    public String userAlias() default "";

    /**
     * 权限字符（用于多个角色匹配符合要求的权限）默认根据权限注解@ss获取，多个权限用逗号分隔开来
     * <hr/>
     * <p>
     *     为什么会有这个permission属性，又是来控制什么呢？
     *     一个user会拥有多个role，每个role都负责不同的业务场景
     *     user可以通过permission精确控制哪些role在本次操作能生效
     * </p>
     * <p>
     *     例子：当前user有两个role，分别为Role A（有权限字符串a）和Role B（有权限字符串b），分别是Dept A和Dept B部门下的角色。
     *     而method接口能都返回这两个部门的数据，此时有个业务需求是user访问method这个接口希望仅返回Dept A的数据。
     *     此时只需要控制permission中的权限让Role A有而Role B没有即可，即permission = "a"就能让Role A生效而Role B失效
     * </p>
     */
    public String permission() default "";
}
