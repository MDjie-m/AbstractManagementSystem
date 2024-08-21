package com.ruoyi.system.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysInspection;
import com.ruoyi.system.service.ISysInspectionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 考察情况Controller
 * 
 * @author tyc
 * @date 2024-07-21
 */
@RestController
@RequestMapping("/system/inspection")
public class SysInspectionController extends BaseController
{
    @Autowired
    private ISysInspectionService sysInspectionService;

//    下面两个参数在uploadFiles里面使用
    //    返回目录的根目录，即http://localhost:8080，或者时服务器的域名
    @Value("${staticFile.rootPath}")
    private String rootPath;
    //    这是构建了一个目录，这个目录在执行下面的方法时如果不存在会在actualName下会自动创建，这个名字的含义是 考察_图片_视频
    private static final String inspectFile= "INSPECT_IMAGE_VIDEO";

    /**
     * 查询考察情况列表
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:list')")
    @PostMapping("/list")
    public TableDataInfo list(SysInspection sysInspection)
    {
        startPage();
        List<SysInspection> list = sysInspectionService.selectSysInspectionList(sysInspection);
        return getDataTable(list);
    }

    /**
     * 导出考察情况列表
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:export')")
    @Log(title = "考察情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysInspection sysInspection)
    {
        List<SysInspection> list = sysInspectionService.selectSysInspectionList(sysInspection);
        ExcelUtil<SysInspection> util = new ExcelUtil<>(SysInspection.class);
        util.exportExcel(response, list, "考察情况数据");
    }

    /**
     * 获取考察情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:query')")
    @GetMapping(value = "/{inspectionId}")
    public AjaxResult getInfo(@PathVariable("inspectionId") String inspectionId)
    {
        return success(sysInspectionService.selectSysInspectionByInspectionId(inspectionId));
    }

    /**
     * 新增考察情况
     * @param sysInspection 考察情况
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:add')")
    @Log(title = "考察情况", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysInspection sysInspection)
    {
        return toAjax(sysInspectionService.insertSysInspection(sysInspection,sysInspection.getFutureField1()));
    }

    /**
     * 修改考察情况
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:edit')")
    @Log(title = "考察情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysInspection sysInspection)
    {
        return toAjax(sysInspectionService.updateSysInspection(sysInspection));
    }

    /**
     * 删除考察情况
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:remove')")
    @Log(title = "考察情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{inspectionIds}")
    public AjaxResult remove(@PathVariable String[] inspectionIds)
    {
        return toAjax(sysInspectionService.deleteSysInspectionByInspectionIds(inspectionIds));
    }

    /**
     * 考察员上传视频和图片
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:upload')")
    @PostMapping("/upload")
    public AjaxResult uploadFiles(@RequestParam("file") MultipartFile file, @RequestParam String supplierId) throws Exception{
        //准备好一个List
        String url ="";
        String filename = file.getOriginalFilename();
        if (filename != null) {
            if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg")) {
                // 这是图片
                url = FileUploadUtils.upload(RuoYiConfig.getProfile()+"/"+inspectFile+"/"+supplierId,
                        file, MimeTypeUtils.IMAGE_EXTENSION);
            } else if (filename.endsWith(".mp4") || filename.endsWith(".avi")) {
                // 这是MP4视频
                url = FileUploadUtils.upload(RuoYiConfig.getProfile()+"/"+inspectFile+"/"+supplierId,
                        file, MimeTypeUtils.VIDEO_EXTENSION);
            }else {
                throw new RuntimeException("格式不正确请重新上传");
            }
        }
        //        拼接url
        url = rootPath + url;
        //把这个url加到urlList列表里面
        return success(url);
    }
}
