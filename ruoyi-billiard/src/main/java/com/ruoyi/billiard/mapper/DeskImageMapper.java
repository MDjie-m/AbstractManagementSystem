package com.ruoyi.billiard.mapper;

import java.util.List;
import com.ruoyi.billiard.domain.DeskImage;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 球桌抓拍等Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@Mapper
public interface DeskImageMapper extends MyBaseMapper<DeskImage>
{
    /**
     * 查询球桌抓拍等
     * 
     * @param deskMultimediaId 球桌抓拍等主键
     * @return 球桌抓拍等
     */
    public DeskImage selectDeskImageByDeskMultimediaId(Long deskMultimediaId);

    /**
     * 查询球桌抓拍等列表
     * 
     * @param deskImage 球桌抓拍等
     * @return 球桌抓拍等集合
     */
    public List<DeskImage> selectDeskImageList(DeskImage deskImage);


    /**
     * 删除球桌抓拍等
     * 
     * @param deskMultimediaId 球桌抓拍等主键
     * @return 结果
     */
    public int deleteDeskImageByDeskMultimediaId(Long deskMultimediaId);

    /**
     * 批量删除球桌抓拍等
     * 
     * @param deskMultimediaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeskImageByDeskMultimediaIds(Long[] deskMultimediaIds);
}
