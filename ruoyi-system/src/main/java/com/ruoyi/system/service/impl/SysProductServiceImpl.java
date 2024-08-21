package com.ruoyi.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.dto.SysProDuctDTO;
import com.ruoyi.system.domain.vo.SysProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysProductMapper;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.service.ISysProductService;

/**
 * 产品Service业务层处理
 * 
 * @author xgg
 * @date 2024-07-23
 */
@Service
public class SysProductServiceImpl implements ISysProductService {
    private static final Logger log = LoggerFactory.getLogger(SysProductServiceImpl.class);

    @Autowired
    private SysProductMapper sysProductMapper;

    /**
     * 查询产品
     * 
     * @param sysProDuctDTO 产品
     * @return 产品
     */
    @Override
    public SysProductVO selectSysProductByProductId(SysProDuctDTO sysProDuctDTO)
    {
        return sysProductMapper.selectSysProductByProductId(sysProDuctDTO);
    }

    /**
     * 查询产品列表
     * 
     * @param sysProDuctDTO 产品
     * @return 产品
     */
    @Override
    public List<SysProductVO> selectSysProductList(SysProDuctDTO sysProDuctDTO)
    {
        return sysProductMapper.selectSysProductList(sysProDuctDTO);
    }

    /**
     * 新增产品
     * 
     * @param sysProduct 产品
     * @return 结果
     */
    @Override
    public int insertSysProduct(SysProduct sysProduct)
    {
        sysProduct.setProductId(UUID.randomUUID().toString());
        return sysProductMapper.insertSysProduct(sysProduct);
    }

    /**
     * 修改产品
     * 
     * @param sysProduct 产品
     * @return 结果
     */
    @Override
    public int updateSysProduct(SysProduct sysProduct)
    {
        return sysProductMapper.updateSysProduct(sysProduct);
    }

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品主键
     * @return 结果
     */
    @Override
    public int deleteSysProductByProductIds(List<String> productIds)
    {
        return sysProductMapper.deleteSysProductByProductIds(productIds);
    }

    /**
     * 删除产品信息
     * 
     * @param productId 产品主键
     * @return 结果
     */
    @Override
    public int deleteSysProductByProductId(String productId)
    {
        return sysProductMapper.deleteSysProductByProductId(productId);
    }

    /**
     * 导入产品数据
     *
     * @param productList     产品数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return
     */
    @Override
    public String importProduct(List<SysProduct> productList, Boolean isUpdateSupport) {
//        if (StringUtils.isNull(productList) || productList.size() == 0) {
//            throw new ServiceException("导入产品数据不能为空！");
//        }
//        int successNum = 0;
//        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
//        StringBuilder failureMsg = new StringBuilder();
//        for (SysProduct sysProduct : productList) {
//            try {
//                // 验证
//                List<SysProduct> list = sysProductMapper.selectSysProductList(sysProduct);
//                if (list.isEmpty()) {
//                    sysProduct.setProductId(UUID.randomUUID().toString());
//                    sysProductMapper.insertSysProduct(sysProduct);
//                    successNum++;
//                    successMsg.append("<br/>" + successNum + "、产品 " + sysProduct.getProductName() + " 导入成功");
//                } else if (isUpdateSupport) {
//                    sysProductMapper.updateSysProduct(sysProduct);
//                    successNum++;
//                    successMsg.append("<br/>" + successNum + "、产品 " + sysProduct.getProductName() + " 更新成功");
//                } else {
//                    failureNum++;
//                    failureMsg.append("<br/>" + failureNum + "、产品 " + sysProduct.getProductName() + " 已存在");
//                }
//            } catch (Exception e) {
//                failureNum++;
//                String msg = "<br/>" + failureNum + "、产品 " + sysProduct.getProductName() + " 导入失败：";
//                failureMsg.append(msg + e.getMessage());
//                log.error(msg, e);
//            }
//        }
//        if (failureNum > 0) {
//            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
//            throw new ServiceException(failureMsg.toString());
//        } else {
//            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
//        }
        return successMsg.toString();
    }
    /**
     * 修改产品状态前端切换状态
     *
     * @param productId 产品id
     * @return 结果
     */
    @Override
    public int updateStatus(String productId,String status) {
        String res = "0";
        if("0".equals(status)){
            res = "1";
        }
        return sysProductMapper.updateStatus(productId,res);

    }
}
