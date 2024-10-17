package com.renxin.psychology.service.impl;

import cn.hutool.core.util.StrUtil;
import com.renxin.common.core.domain.entity.SysDictData;
import com.renxin.common.core.domain.entity.SysDictType;
import com.renxin.common.exception.ServiceException;
import com.renxin.common.utils.NewDateUtil;
import com.renxin.psychology.domain.PsyConsultOrder;
import com.renxin.psychology.dto.DateNumDTO;
import com.renxin.psychology.mapper.PsyConsultConfigMapper;
import com.renxin.psychology.service.IPsyConsultConfigService;
import com.renxin.psychology.service.IPsyConsultOrderService;
import com.renxin.psychology.vo.PsyConsultConfigByGroupVO;
import com.renxin.psychology.vo.PsyConsultConfigVO;
import com.renxin.system.service.ISysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 咨询类型Service业务层处理
 * 
 * @author renxin
 * @date 2023-06-16
 */
@Service
@Slf4j
public class PsyConsultConfigServiceImpl implements IPsyConsultConfigService
{

    @Resource
    private PsyConsultConfigMapper psyConsultConfigMapper;

    @Resource
    private ISysDictTypeService iSysDictTypeService;

    @Resource
    private IPsyConsultOrderService orderService;

    @Override
    public List<DateNumDTO> getDateNum(Integer num) {
        List<DateNumDTO> list = new ArrayList<>();
        if (num == 0) {
            return list;
        }
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        for (int i = 1; i <= num; i++) {
            if (i > 1) {
                calendar.add(Calendar.DATE, 1);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fm = sdf.format(calendar.getTime());
            String[] format = StringUtils.split(fm, "-");

            DateNumDTO dto = new DateNumDTO();
            dto.setDate(fm);
            dto.setYear(format[0]);
            dto.setMonth(format[1] + "月");
            dto.setWeek(NewDateUtil.getWeekOfDate(calendar));
            dto.setDay(format[2]);
            list.add(dto);
        }

        return list;
    }

    @Override
    public List<PsyConsultConfigVO> getConfigByType(String dictType) {
        SysDictType sysDictType = iSysDictTypeService.selectDictTypeByType(dictType);
        List<SysDictData> dictData = iSysDictTypeService.selectDictDataByType(dictType);
        List<PsyConsultConfigVO> collect = dictData.stream().map(i -> convertToVo(sysDictType, i)).collect(Collectors.toList());
        return collect;
    }

    private PsyConsultConfigVO convertToVo(SysDictType sysDictType, SysDictData sysDictData) {
        PsyConsultConfigVO vo = new PsyConsultConfigVO();
        vo.setTitle(sysDictType.getDictName());
        vo.setCode(sysDictData.getDictType());
        vo.setName(sysDictData.getDictLabel());
        vo.setValue(sysDictData.getDictValue());
        return vo;
    }

    @Override
    public List<PsyConsultConfigByGroupVO> getConfigByTypes(String[] dictTypes) {
        return psyConsultConfigMapper.getConfigByTypes(dictTypes);
    }

    @Override
    public List<String> getNotices() {
        try{
            List<SysDictData> dictData = iSysDictTypeService.selectDictDataByType("consult_notice");
            List<String> list = dictData.stream().map(SysDictData::getDictLabel).collect(Collectors.toList());
    
            List<PsyConsultOrder> orders = orderService.getListForNotice();
            orders.forEach(a -> {
                list.add(StrUtil.format("{}** 下单了{}老师的{}!", getFirstEmoji(a.getNickName()), a.getConsultName(), a.getServeName()));
            });
    
            Collections.shuffle(list);
            return list;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new ServiceException("getNotices查看最近通知异常");
        }
    }

    private String getFirstEmoji(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        String regex = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if (matcher.find() && matcher.start() == 0) {
            return matcher.group();
        }
        return name.substring(0, 1);
    }

}
