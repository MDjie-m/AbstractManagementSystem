package com.renxin.advert.service.impl;


import com.renxin.advert.domain.PsyAdvert;
import com.renxin.advert.domain.PsyAdvertItem;
import com.renxin.advert.mapper.PsyAdvertMapper;
import com.renxin.advert.service.IPsyAdvertItemService;
import com.renxin.advert.service.IPsyAdvertService;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.DateUtils;
import com.renxin.course.domain.CourCourse;
import com.renxin.course.service.ICourCourseService;
import com.renxin.course.vo.CourseListVO;
import com.renxin.psychology.domain.PsyConsultantTeamSupervision;
import com.renxin.psychology.service.IPsyConsultantTeamSupervisionService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 页面广告Service业务层处理
 * 
 * @author renxin
 * @date 2024-08-16
 */
@Service
public class PsyAdvertServiceImpl implements IPsyAdvertService 
{
    @Autowired
    private PsyAdvertMapper psyAdvertMapper;
    
    @Autowired
    private IPsyAdvertItemService advertItemService;

    @Autowired
    private IPsyConsultantTeamSupervisionService teamSupervisionService;

    @Autowired
    private ICourCourseService courCourseService;

    /**
     * 查询各类型的对象清单
     */
    public AjaxResult queryObjectByIds(PsyAdvert req){
        String ids = req.getIds();
        List<String> idList = Arrays.asList(ids.split(","));
        String dataType = req.getDataType();

        switch (dataType){
            case "团队督导":
                PsyConsultantTeamSupervision teamReq = new PsyConsultantTeamSupervision();
                teamReq.setIdList(idList);
                List<PsyConsultantTeamSupervision> list1 = teamSupervisionService.selectPsyConsultantTeamSupervisionList(teamReq);
                return AjaxResult.success(list1);
            case "course":
                CourCourse courCourseReq = new CourCourse();
                courCourseReq.setIdList(idList.stream().map(Long::valueOf).collect(Collectors.toList()));
                List<CourseListVO> list2 = courCourseService.getCourseListByClassId(courCourseReq);
                return AjaxResult.success(list2);
                
        }

        return AjaxResult.success();
    }
    
    
    /**
     * 查询页面广告
     * 
     * @param advertNo 页面广告主键
     * @return 页面广告
     */
    @Override
    public PsyAdvert selectPsyAdvertByAdvertNo(String advertNo)
    {
        PsyAdvert advert = psyAdvertMapper.selectPsyAdvertByAdvertNo(advertNo);

        //查询条目清单
        PsyAdvertItem itemReq = new PsyAdvertItem();
            itemReq.setAdvertNo(advert.getAdvertNo());
        List<PsyAdvertItem> itemList = advertItemService.selectPsyAdvertItemList(itemReq);
        
        advert.setItemList(itemList);

        return advert;
    }

    /**
     * 查询页面广告列表
     * 
     * @param psyAdvert 页面广告
     * @return 页面广告
     */
    @Override
    public List<PsyAdvert> selectPsyAdvertList(PsyAdvert psyAdvert)
    {
        return psyAdvertMapper.selectPsyAdvertList(psyAdvert);
    }

    /**
     * 新增页面广告
     * 
     * @param psyAdvert 页面广告
     * @return 结果
     */
    @Override
    public int insertPsyAdvert(PsyAdvert psyAdvert)
    {
        PsyAdvert oldAdvert = psyAdvertMapper.selectPsyAdvertByAdvertNo(psyAdvert.getAdvertNo());
        if (ObjectUtils.isNotEmpty(oldAdvert)){
            throw new ServiceException("该广告编号已存在, 请更换");
        }
        
        psyAdvert.setCreateTime(DateUtils.getNowDate());
        return psyAdvertMapper.insertPsyAdvert(psyAdvert);
    }

    /**
     * 修改页面广告
     * 
     * @param psyAdvert 页面广告
     * @return 结果
     */
    @Override
    public int updatePsyAdvert(PsyAdvert advert)
    {
        advert.setUpdateTime(DateUtils.getNowDate());
        int i = psyAdvertMapper.updatePsyAdvert(advert);

        //删除旧条目
        advertItemService.deleteItemByAdvertNo(advert.getAdvertNo());
        if (ObjectUtils.isNotEmpty(advert.getItemList())){
            //批量新增条目
            advertItemService.insertPsyAdvertItemList(advert.getItemList());
        }
        
        return i;
    }

    /**
     * 批量删除页面广告
     * 
     * @param advertNos 需要删除的页面广告主键
     * @return 结果
     */
    @Override
    public int deletePsyAdvertByAdvertNos(String[] advertNos)
    {
        return psyAdvertMapper.deletePsyAdvertByAdvertNos(advertNos);
    }

    /**
     * 删除页面广告信息
     * 
     * @param advertNo 页面广告主键
     * @return 结果
     */
    @Override
    public int deletePsyAdvertByAdvertNo(String advertNo)
    {
        int i = psyAdvertMapper.deletePsyAdvertByAdvertNo(advertNo);
        advertItemService.deleteItemByAdvertNo(advertNo);
        return i;
    }
}
