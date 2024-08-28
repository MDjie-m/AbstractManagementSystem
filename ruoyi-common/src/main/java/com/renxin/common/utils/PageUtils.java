package com.renxin.common.utils;

import com.github.pagehelper.PageHelper;
import com.renxin.common.core.page.PageDomain;
import com.renxin.common.core.page.TableSupport;
import com.renxin.common.utils.sql.SqlUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 * 
 * @author renxin
 */
public class PageUtils extends PageHelper
{
    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }

    //list分页处理
    public static <T> List<T> paginate(List<T> list) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        
        if (list == null || list.isEmpty() || pageNum < 1 || pageSize < 1) {
            return new ArrayList<>(); // 返回一个空的列表
        }

        int fromIndex = (pageNum - 1) * pageSize;
        if (fromIndex >= list.size()) {
            return new ArrayList<>(); // 如果起始索引超出列表大小，返回空列表
        }

        // 计算结束索引，确保它不会超过列表的大小
        int toIndex = Math.min(fromIndex + pageSize, list.size());

        return list.subList(fromIndex, toIndex); // 裁剪出的子列表
    }
}
