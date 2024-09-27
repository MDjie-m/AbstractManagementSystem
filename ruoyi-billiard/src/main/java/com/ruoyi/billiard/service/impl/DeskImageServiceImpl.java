package com.ruoyi.billiard.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.billiard.mapper.DeskImageMapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.billiard.domain.DeskImage;
import com.ruoyi.billiard.service.IDeskImageService;

/**
 * 球桌抓拍等Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-25
 */
@Service
public class DeskImageServiceImpl extends ServiceImpl<DeskImageMapper, DeskImage> implements IDeskImageService {


    /**
     * 查询球桌抓拍等
     *
     * @param deskMultimediaId 球桌抓拍等主键
     * @return 球桌抓拍等
     */
    @Override
    public DeskImage selectDeskImageByDeskMultimediaId(Long deskMultimediaId) {
        return baseMapper.selectById(deskMultimediaId);
    }

    /**
     * 查询球桌抓拍等列表
     *
     * @param deskImage 球桌抓拍等
     * @return 球桌抓拍等
     */
    @Override
    public List<DeskImage> selectDeskImageList(DeskImage deskImage) {
        return baseMapper.selectDeskImageList(deskImage);
    }

    /**
     * 新增球桌抓拍等
     *
     * @param deskImage 球桌抓拍等
     * @return 结果
     */
    @Override
    public int insertDeskImage(DeskImage deskImage) {
         deskImage.setCreateById(0L );
         deskImage.setCreateBy("system");
        deskImage.setCaptureTime(new Date());
        deskImage.setDeskMultimediaId(IdUtils.singleNextId());
        return baseMapper.insert(deskImage);
    }

    /**
     * 修改球桌抓拍等
     *
     * @param deskImage 球桌抓拍等
     * @return 结果
     */
    @Override
    public int updateDeskImage(DeskImage deskImage) {
        SecurityUtils.fillUpdateUser(deskImage);

        return baseMapper.updateById(deskImage);
    }

    /**
     * 批量删除球桌抓拍等
     *
     * @param deskMultimediaIds 需要删除的球桌抓拍等主键
     * @return 结果
     */
    @Override
    public int deleteDeskImageByDeskMultimediaIds(Long[] deskMultimediaIds) {
        return baseMapper.deleteDeskImageByDeskMultimediaIds(deskMultimediaIds);
    }

    /**
     * 删除球桌抓拍等信息
     *
     * @param deskMultimediaId 球桌抓拍等主键
     * @return 结果
     */
    @Override
    public int deleteDeskImageByDeskMultimediaId(Long deskMultimediaId) {
        return baseMapper.deleteDeskImageByDeskMultimediaId(deskMultimediaId);
    }

    @Override
    public Boolean addCapture(Long storeId, Long deskId, Long orderId, Long cameraId) {
        DeskImage img = new DeskImage();
        img.setDeskId(deskId);
        img.setStoreId(storeId);
        img.setCaptureTime(new Date());
        img.setDeskMultimediaId(IdUtils.singleNextId());
        img.setCameraId(Optional.ofNullable(cameraId).orElse(0L));
        img.setOrderId(orderId);
        img.setFilePath("");
        return insertDeskImage(img) > 0;
    }
}
