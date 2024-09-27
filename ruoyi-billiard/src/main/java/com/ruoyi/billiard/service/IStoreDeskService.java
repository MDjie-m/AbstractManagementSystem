package com.ruoyi.billiard.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ruoyi.billiard.domain.AddDeskScoreReqVo;
import com.ruoyi.billiard.domain.DeskCaptureReqVo;
import com.ruoyi.billiard.domain.Store;
import com.ruoyi.billiard.domain.StoreDesk;
import com.ruoyi.billiard.domain.vo.CashierDeskDashboardResVo;
import com.ruoyi.billiard.domain.vo.DeskQueryResVo;
import com.ruoyi.billiard.domain.vo.LineUpVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * 球桌Service接口
 *
 * @author ruoyi
 * @date 2024-09-07
 */
public interface IStoreDeskService {
    /**
     * 查询球桌
     *
     * @param deskId 球桌主键
     * @return 球桌
     */
    public StoreDesk selectStoreDeskByDeskId(Long deskId);

    /**
     * 查询球桌列表
     *
     * @param storeDesk 球桌
     * @return 球桌集合
     */
    public List<StoreDesk> selectStoreDeskList(StoreDesk storeDesk);

    /**
     * 新增球桌
     *
     * @param storeDesk 球桌
     * @return 结果
     */
    public int insertStoreDesk(StoreDesk storeDesk);

    /**
     * 修改球桌
     *
     * @param storeDesk 球桌
     * @return 结果
     */
    public int updateStoreDesk(StoreDesk storeDesk);

    /**
     * 批量删除球桌
     *
     * @param deskIds 需要删除的球桌主键集合
     * @return 结果
     */
    public int deleteStoreDeskByDeskIds(Long[] deskIds);

    /**
     * 删除球桌信息
     *
     * @param deskId 球桌主键
     * @return 结果
     */
    public int deleteStoreDeskByDeskId(Long deskId);

    List<Store> getByLoginUserId(Long loginUserId);

    CashierDeskDashboardResVo getDeskDashboard(Long storeId);

    Map<Integer, LineUpVo> getLineUpInfo(Long storeId);

    Boolean saveLineUpInfo(Long storeId, Map<Integer, LineUpVo> reqVo);

    DeskQueryResVo queryDestCurrentInfo(Long deskId, Long storeId);

    DeskQueryResVo startCalcFee(Long deskId, Long storeId);


    DeskQueryResVo swapToNewDesk(Long deskId, Long storeId, Long orderId, Long newDeskId);

    DeskQueryResVo pauseCalcFee(Long deskId, Long storeId);


    DeskQueryResVo resumeDesk(Long deskId, Long storeId);

    DeskQueryResVo mergeToNewDesk(Long deskId, Long storeId, Long orderId, Long newDeskId);

    Boolean addScore(AddDeskScoreReqVo reqVo );

    Boolean addCapture(DeskCaptureReqVo reqVo  );
}
