package com.stdiet.web.controller.custom;

import com.stdiet.common.core.controller.BaseController;
import com.stdiet.common.core.domain.AjaxResult;
import com.stdiet.common.core.page.TableDataInfo;
import com.stdiet.common.utils.StringUtils;
import com.stdiet.common.utils.oss.AliyunOSSUtils;
import com.stdiet.custom.domain.SysCustomerCase;
import com.stdiet.custom.domain.SysCustomerCaseFile;
import com.stdiet.custom.dto.response.CustomerCaseResponse;
import com.stdiet.custom.service.ISysCustomerCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wx/customerCase")
public class WxCustomerCaseController extends BaseController{

    @Autowired
    private ISysCustomerCaseService sysCustomerCaseService;

    /**
     * 查询微信小程序中展示的客户案例
     */
    @GetMapping("/caseList")
    public TableDataInfo caseList(SysCustomerCase sysCustomerCase)
    {
        startPage();
        sysCustomerCase.setKeywordArray(StringUtils.isNotEmpty(sysCustomerCase.getKeyword()) ? sysCustomerCase.getKeyword().split(",") : null);
        //sysCustomerCase.setWxShow(1);
        List<SysCustomerCase> list = sysCustomerCaseService.selectSysCustomerCaseList(sysCustomerCase);
        //List<CustomerCaseResponse> resultList = dealSysCustomerCase(list);
        return getDataTable(list);
    }

    /**
     * 查询客户案例文件列表
     */
    @GetMapping("/getFileByCaseId")
    public AjaxResult getFileByCaseId(@RequestParam("caseId")Long caseId)
    {
        CustomerCaseResponse customerCaseResponse = new CustomerCaseResponse();
        List<SysCustomerCaseFile> list = sysCustomerCaseService.getFileListByCaseId(caseId);
        List<String> fileUrl = new ArrayList<>();
        for (SysCustomerCaseFile caseFile : list) {
            fileUrl.add(caseFile.getFileUrl());
        }
        List<String> downUrlList = AliyunOSSUtils.generatePresignedUrl(fileUrl);
        customerCaseResponse.setFileList(downUrlList);
        return AjaxResult.success(customerCaseResponse);
    }

    /**
     * 处理返回值
     * @param list
     * @return
     */
    private List<CustomerCaseResponse> dealSysCustomerCase(List<SysCustomerCase> list){
        List<CustomerCaseResponse> resultList = new ArrayList<>();
        for (SysCustomerCase customerCase : list) {
            CustomerCaseResponse customerCaseResponse = new CustomerCaseResponse();
            customerCaseResponse.setId(customerCase.getId());
            customerCaseResponse.setKeyword(customerCase.getKeyword());
            customerCaseResponse.setName(customerCase.getName());
            customerCaseResponse.setRemark(customerCase.getRemark());
            resultList.add(customerCaseResponse);
        }
        return resultList;
    }
}
