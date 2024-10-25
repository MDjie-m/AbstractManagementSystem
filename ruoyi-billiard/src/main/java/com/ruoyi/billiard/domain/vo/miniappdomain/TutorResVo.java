package com.ruoyi.billiard.domain.vo.miniappdomain;

import com.ruoyi.billiard.domain.StoreTutor;
import com.ruoyi.billiard.enums.TutorLevel;
import lombok.Data;

import java.util.List;
@Data
public class TutorResVo {

    private TutorLevel level;
    private List<StoreTutor> tutors;

}
