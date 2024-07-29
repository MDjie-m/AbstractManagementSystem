package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.vo.AuditVo;
import com.ruoyi.system.easyexcel.SupplierListener;
import com.ruoyi.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.service.ISysSupplierService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 供应商Service业务层处理
 *
 * @author xgg
 * @date 2024-07-21
 */
@Service
public class SysSupplierServiceImpl implements ISysSupplierService
{
    @Autowired
    private SysSupplierMapper sysSupplierMapper;

    @Autowired
    private SysInspectionMapper sysInspectionMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;


    /**
     * 查询供应商
     *
     * @param supplierId 供应商主键
     * @return 供应商
     */
    @Override
    public SysSupplier selectSysSupplierBySupplierId(String supplierId)
    {
        return sysSupplierMapper.selectSysSupplierBySupplierId(supplierId);
    }

    /**
     * 查询供应商列表
     *
     * @param sysSupplier 供应商
     * @return 供应商
     */
    @Override
    public List<SysSupplier> selectSysSupplierList(SysSupplier sysSupplier)
    {
        return sysSupplierMapper.selectSysSupplierList(sysSupplier);
    }

    /**
     * 新增供应商
     *
     * @param sysSupplier 供应商
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysSupplier(SysSupplier sysSupplier)
    {
        String supplierNameCn = sysSupplier.getSupplierNameCn();
        SysUser user = new SysUser();
        user.setUserName(supplierNameCn);
        user.setNickName(supplierNameCn);
        user.setPassword(SecurityUtils.encryptPassword("123456"));
        user.setUserType("5");
        int i = userMapper.insertUser(user);
        Long userId = user.getUserId();
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        SysUserRole ur = new SysUserRole();
        ur.setUserId(userId);
        ur.setRoleId(Long.parseLong("5"));
        list.add(ur);
        userRoleMapper.batchUserRole(list);
        sysSupplier.setSupplierId(UUID.randomUUID().toString());
        int rows = sysSupplierMapper.insertSysSupplier(sysSupplier);
        insertSysProduct(sysSupplier);
        return rows;

    }

    /**
     * 修改供应商
     *
     * @param sysSupplier 供应商
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysSupplier(SysSupplier sysSupplier)
    {
        sysSupplierMapper.deleteSysProductBySupplierId(sysSupplier.getSupplierId());
        insertSysProduct(sysSupplier);
        return sysSupplierMapper.updateSysSupplier(sysSupplier);
    }

    /**
     * 批量删除供应商
     *
     * @param supplierIds 需要删除的供应商主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysSupplierBySupplierIds(String[] supplierIds)
    {
        sysSupplierMapper.deleteSysProductBySupplierIds(supplierIds);
        return sysSupplierMapper.deleteSysSupplierBySupplierIds(supplierIds);
    }

    /**
     * 删除供应商信息
     *
     * @param supplierId 供应商主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysSupplierBySupplierId(String supplierId)
    {
        sysSupplierMapper.deleteSysProductBySupplierId(supplierId);
        return sysSupplierMapper.deleteSysSupplierBySupplierId(supplierId);
    }

    /**
     * 批量保存
     * @param file 上传的excel文件
     * @return
     */
    @Override
    public void saveSysSupplier(MultipartFile file) throws IOException {
        EasyExcel
                .read(file.getInputStream(), SysSupplier.class, new SupplierListener(sysSupplierMapper))
                .sheet()
                .doRead();
    }

    /**
     * 导出供应商列表
     * @param response
     * @param supplier 查询条件
     * @throws IOException
     */
    @Override
    public void exportSysSupplier(HttpServletResponse response, SysSupplier supplier) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("供应商列表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 表头大小
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)12);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        // 查询供应商数据
        List<SysSupplier> list = sysSupplierMapper.selectSysSupplierList(supplier);
        // 把查询到的数据导出至excel
        EasyExcel
                .write(response.getOutputStream(), SysSupplier.class)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()) // 设置自动调整列宽
                .sheet("供应商数据")
                .doWrite(list);
    }

    /**
     * 编辑供应商审核状态
     * @param vo
     * @return
     */
    @Override
    public int auditSysSupplier(AuditVo vo) {
        int count = 0;
        // auditStatus = 1 通过审核
        if (vo.getAuditStatus() == 1) {
            count = sysSupplierMapper
                    .editSysSupplierAudit(vo.getSupplierList(),
                            vo.getAuditStatus(),
                            0, // 修改考察状态为候选
                            vo.getRemark());
        }else{
            count = sysSupplierMapper
                    .editSysSupplierAudit(vo.getSupplierList(),
                            vo.getAuditStatus(),
                            null, // 考察状态为空
                            vo.getRemark());
        }
        return count;
    }

    /**
     * 新增产品信息
     *
     * @param sysSupplier 供应商对象
     */
    public void insertSysProduct(SysSupplier sysSupplier)
    {
        List<SysProduct> sysProductList = sysSupplier.getSysProductList();
        String supplierId = sysSupplier.getSupplierId();
        if (StringUtils.isNotNull(sysProductList))
        {
            List<SysProduct> list = new ArrayList<SysProduct>();
            for (SysProduct sysProduct : sysProductList)
            {
                sysProduct.setSupplierId(supplierId);
                list.add(sysProduct);
            }
            if (list.size() > 0)
            {
                sysSupplierMapper.batchSysProduct(list);
            }
        }
    }

    /**
     * 执行xml里面的sql语句将审核通过的供应商的考察状态变更为待考察
     * @param supplierIds 供应商id列表
     * @return
     */
    @Override
    public int inspectSysSupplier(List<String> supplierIds) {
        return sysSupplierMapper.inspectSysSupplier(supplierIds);
    }

    /**
     * 审核考察
     * @param vo
     * @return
     */
    @Override
    public int inspectionAuditSysSupplier(AuditVo vo) {
        sysSupplierMapper.inspectionAuditSysSupplier(vo.getSupplierList(), vo.getAuditStatus(),vo.getRate());
        return sysInspectionMapper.updateInspectAudit(vo.getSupplierList(), vo.getRemark(), vo.getAuditStatus().toString());
    }
}
