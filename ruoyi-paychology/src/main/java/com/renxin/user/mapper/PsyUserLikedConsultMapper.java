package com.renxin.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.renxin.user.domain.PsyUserLikedConsult;
import com.renxin.user.vo.PsyUserLikedConsultVO;

import java.util.List;

/**
 * 咨询服务Mapper接口
 * 
 * @author renxin
 * @date 2023-06-16
 */
public interface PsyUserLikedConsultMapper extends BaseMapper<PsyUserLikedConsult>
{

    PsyUserLikedConsult getLiked(PsyUserLikedConsult psyConsultFocus);

    List<PsyUserLikedConsultVO> getLikes(Long userId);

    int add(PsyUserLikedConsult psyConsultFocus);

    int del(PsyUserLikedConsult psyConsultFocus);

}
