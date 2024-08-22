package com.renxin.psychology.dto;

import com.renxin.psychology.vo.PsyConsultServeConfigVO;
import com.renxin.psychology.vo.PsyConsultVO;
import com.renxin.psychology.vo.PsyConsultWorkVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 咨询服务对象 psy_consult
 *
 * @author renxin
 * @date 2023-06-25
 */
@Data
public class PsyConsultInfoDTO implements Serializable
{
    private static final long serialVersionUID = -1670719183435508851L;

    private PsyConsultVO consult;

    private PsyConsultServeConfigVO serve;

    private List<PsyConsultWorkVO> works;
    
    //咨询师-服务  关联id
    private Long relationId;

}
