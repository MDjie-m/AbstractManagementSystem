package com.ruoyi.system.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

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

//    下面三个参数在uploadFiles里面使用
    //    服务器或本地实际存放照片或视频的目录路径
    @Value("${staticFile.actualFolder}")
    private String actualName;
    //    返回目录的根目录，即http://localhost:8080，或者时服务器的域名
    @Value("${staticFile.rootPath}")
    private String rootPath;
    //    这是构建了一个目录，这个目录在执行下面的方法时如果不存在会在actualName下会自动创建，这个名字的含义是 考察_图片_视频
    private static final String inspectFile= "INSPECT_IMAGE_VIDEO";

    /**
     * 查询考察情况列表
     */
    @PreAuthorize("@ss.hasPermi('system:inspection:list')")
    @GetMapping("/list")
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
    public AjaxResult uploadFiles(@RequestParam("file") MultipartFile file, @RequestParam String supplierId){
        //准备好一个List
        String url ="";
        //图片视频保存到哪个文件目录下
        File targetFile = new File(actualName+inspectFile+"/"+supplierId);
        try {
            //先判断是否有这个目录
            if(!targetFile.exists()){
                //没有就创建一个
                boolean judge = targetFile.mkdirs();
                if(!judge){//新建失败就返回错误
                    return error("上传失败，服务器资源错误");
                }
            }
            //新建成功就循环遍历传过来的文件数组
                //存放在服务器上的文件前缀统一用uuid
                String fileName = UUID.randomUUID().toString();
                //获取这个文件的后缀，Objects.requireNonNull,当不为null才能截取，当然本身应该是有名字的，但是这里idea提示没判断是否为Null所以就用这个吧
                String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
                //上面的uuid加上后缀就是这个文件在服务器上的文件名
                String newName = fileName+suffix;
                //然后在这个目录下创建一个这个文件
                File targetFileName = new File(targetFile,newName);
                //向这个文件里面写入它的文件内容
                file.transferTo(targetFileName);
                //然后拼接访问这个文件资源的url
                url = rootPath + "/template/" + inspectFile + "/" + supplierId + "/" + newName;
                //把这个url加到urlList列表里面

        }catch (Exception e){
            //捕获的异常这里返回对应的错误信息，这个方法不会返回完整信息，只会返回一句话
            return error(e.getMessage());
        }
        return success(url);
    }
}
