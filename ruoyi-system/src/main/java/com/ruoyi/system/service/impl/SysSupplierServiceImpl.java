package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.domain.vo.AuditVo;
import com.ruoyi.system.domain.vo.supplierVo.SelectSupplierVo;
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
     * @param supplierVo 供应商
     * @return 供应商
     */
    @Override
    public List<SysSupplier> selectSysSupplierList(SelectSupplierVo supplierVo)
    {
        return sysSupplierMapper.selectSysSupplierList(supplierVo);
    }

    @Override
    public List<SysSupplier> selectSupplierByProduct(SelectSupplierVo sysSupplier) {
        return sysSupplierMapper.selectSupplierByProduct(sysSupplier);
    }

    /**
     * 新增供应商
     *
     * @param sysSupplier 供应商
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult insertSysSupplier(SysSupplier sysSupplier)
    {
        // 根据法人手机号判断供应商是否已经存在
        if(sysSupplierMapper.checkLegalPersonTelephoneUnique(sysSupplier.getLegalPersonTelephone()) == null) {
            // 判断法人手机号是否已经被注册为用户
            if (userMapper.checkUserNameUnique(sysSupplier.getLegalPersonTelephone()) == null) {
                // 判断负责人手机号是否已经被注册为用户
                if (userMapper.checkUserNameUnique(sysSupplier.getPrincipalTelephone()) == null) {
                    // 设置供应商id
                    sysSupplier.setSupplierId(UUID.randomUUID().toString());
                    System.out.println(sysSupplier.getSupplierId().length());
                    // 设置供应商入驻时间
                    sysSupplier.setEntryDate(new Date());
                    // 设置数据创建时间
                    sysSupplier.setCreateTime(new Date());

                    String supplierId = sysSupplier.getSupplierId();
                    String legalPersonTelephone = sysSupplier.getLegalPersonTelephone();
                    String principalTelephone = sysSupplier.getPrincipalTelephone();
                    String nickName = sysSupplier.getSupplierNameCn();
                    String legalPassword = legalPersonTelephone.substring(legalPersonTelephone.length() - 6);
                    String principalPassword = principalTelephone.substring(principalTelephone.length() - 6);
                    String legalPersonEmail = sysSupplier.getLegalPersonEmail();
                    String principalEmail = sysSupplier.getPrincipalEmail();

                    // 设置返回值
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("legalPersonUserName",legalPersonTelephone);
                    jsonObject.put("legalPersonPassword",legalPassword);
                    jsonObject.put("principalUserName",principalTelephone);
                    jsonObject.put("principalPassword",principalPassword);

                    // 主账号用户对象
                    SysUser user = new SysUser();
                    // 用户名为法人手机号
                    user.setUserName(legalPersonTelephone);
                    // 用户昵称为公司中文名称
                    user.setNickName(nickName);
                    // 用户密码,使用法人手机号后六位
                    user.setPassword(SecurityUtils.encryptPassword(legalPassword));
                    // 用户角色为5-供应商/商家,6-供应商,7-商家
                    if (sysSupplier.getLabel() == 0) {
                        user.setUserType("5");
                    } else if (sysSupplier.getLabel() == 1) {
                        user.setUserType("6");
                    } else if (sysSupplier.getLabel() == 2) {
                        user.setUserType("7");
                    }
                    // 用户邮箱为法人邮箱
                    user.setEmail(legalPersonEmail);
                    // 手机号码为法人手机号
                    user.setPhonenumber(legalPersonTelephone);
                    // 账号状态 0-正常
                    user.setStatus("0");
                    // 删除标志 0-存在
                    user.setDelFlag("0");
                    // 是否为子账号 0-否
                    user.setSubAccountFlag(0);
                    // 绑定供应商id
                    user.setSupplierId(supplierId);
                    // 注册主账号
                    int i = userMapper.insertUser(user);
                    if (i <= 0) {
                        return AjaxResult.error("注册主账号失败!");
                    }

                    // 负责人用户对象
                    SysUser userChild = new SysUser();
                    // 用户名为负责人手机号
                    userChild.setUserName(principalTelephone);
                    // 用户昵称为公司中文名称
                    userChild.setNickName(nickName);
                    // 用户密码,使用负责人手机号后六位
                    userChild.setPassword(SecurityUtils.encryptPassword(principalPassword));
                    // 用户角色与主账号保持一致
                    userChild.setUserType(user.getUserType());
                    // 用户邮箱为负责人邮箱
                    userChild.setEmail(principalEmail);
                    // 手机号码为负责人手机号
                    userChild.setPhonenumber(principalTelephone);
                    // 账号状态 0-正常
                    userChild.setStatus("0");
                    // 删除标志 0-存在
                    userChild.setDelFlag("0");
                    // 是否为子账号 1-是
                    userChild.setSubAccountFlag(1);
                    // 子账号的父级id
                    userChild.setParentUserId(String.valueOf(user.getUserId()));
                    i = userMapper.insertUser(userChild);
                    if (i <= 0) {
                        return AjaxResult.error("子账号注册失败!");
                    }

                    // 新增用户与角色管理
                    List<SysUserRole> list = new ArrayList<SysUserRole>();
                    SysUserRole ur = new SysUserRole();
                    ur.setUserId(user.getUserId());
                    ur.setRoleId(Long.valueOf(user.getUserType()));
                    list.add(ur);
                    SysUserRole ur1 = new SysUserRole();
                    ur1.setUserId(userChild.getUserId());
                    ur1.setRoleId(Long.valueOf(userChild.getUserType()));
                    list.add(ur1);
                    i = userRoleMapper.batchUserRole(list);
                    if (i <= 0) {
                        return AjaxResult.error("新增用户与角色管理失败!");
                    }

                    // 新增供应商
                    int rows = sysSupplierMapper.insertSysSupplier(sysSupplier);
                    // 添加产品
                    insertSysProduct(sysSupplier);
                    if (rows > 0) {
                        return AjaxResult.success(jsonObject);
                    } else {
                        return AjaxResult.error();
                    }
                }
                else {
                    return AjaxResult.error("负责人联系方式已被注册为用户!");
                }
            } else {
                return AjaxResult.error("法人联系方式已被注册为用户!");
            }
        } else {
            return AjaxResult.error("此联系方式已被注册为供应商!");
        }
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
        sysSupplier.setUpdateTime(new Date());
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
     * @param supplierVo 查询条件
     * @throws IOException
     */
    @Override
    public void exportSysSupplier(HttpServletResponse response, SelectSupplierVo supplierVo) throws IOException {
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
        List<SysSupplier> list = sysSupplierMapper.selectSysSupplierList(supplierVo);
        System.out.println(list.get(0));
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
        String supplierName = sysSupplier.getSupplierNameCn();
        if (StringUtils.isNotNull(sysProductList))
        {
            List<SysProduct> list = new ArrayList<SysProduct>();
            for (SysProduct sysProduct : sysProductList)
            {
                sysProduct.setProductId(UUID.randomUUID().toString());
                sysProduct.setSupplierId(supplierId);
                sysProduct.setSupplierName(supplierName);
                // 是主营产品
                sysProduct.setMainProduct(1);
                // 删除标识 0-未删除
                sysProduct.setDelFlag(0);
                // 未报价
                sysProduct.setQuoteStatus(0);
                // 未询价
                sysProduct.setInquiryStatus(0);
                // 不常报价
                sysProduct.setQuoteListFlag(0);
                // 不常询价
                sysProduct.setInquiryListFlag(0);
                // 创建时间
                sysProduct.setCreateTime(new Date());
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
