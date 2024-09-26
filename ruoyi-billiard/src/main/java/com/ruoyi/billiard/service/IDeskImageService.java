package com.ruoyi.billiard.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.billiard.domain.DeskImage;

/**
 * 球桌抓拍等Service接口
 *
 * @author ruoyi
 * @date 2024-09-25
 */
public interface IDeskImageService extends IService<DeskImage> {
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
     * 新增球桌抓拍等
     *
     * @param deskImage 球桌抓拍等
     * @return 结果
     */
    public int insertDeskImage(DeskImage deskImage);

    /**
     * 修改球桌抓拍等
     *
     * @param deskImage 球桌抓拍等
     * @return 结果
     */
    public int updateDeskImage(DeskImage deskImage);

    /**
     * 批量删除球桌抓拍等
     *
     * @param deskMultimediaIds 需要删除的球桌抓拍等主键集合
     * @return 结果
     */
    public int deleteDeskImageByDeskMultimediaIds(Long[] deskMultimediaIds);

    /**
     * 删除球桌抓拍等信息
     *
     * @param deskMultimediaId 球桌抓拍等主键
     * @return 结果
     */
    public int deleteDeskImageByDeskMultimediaId(Long deskMultimediaId);

    Boolean addCapture(Long storeId, Long deskId, Long orderId,Long cameraId);
}
