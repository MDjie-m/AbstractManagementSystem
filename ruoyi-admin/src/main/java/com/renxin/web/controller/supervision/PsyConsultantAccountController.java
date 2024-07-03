package com.renxin.web.controller.supervision;

import com.renxin.common.annotation.Log;
import com.renxin.common.core.controller.BaseController;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.core.page.TableDataInfo;
import com.renxin.common.enums.BusinessType;
import com.renxin.common.utils.poi.ExcelUtil;
import com.renxin.psychology.domain.PsyConsultantAccount;
import com.renxin.psychology.service.IPsyConsultantAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 账户Controller
 * 
 * @author renxin
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/finance/account")
public class PsyConsultantAccountController extends BaseController
{
    @Autowired
    private IPsyConsultantAccountService psyConsultantAccountService;

    /**
     * 初始化心理咨询师账户。
     *
     * 通过该接口调用，可以为系统中的心理咨询师初始化账户。
     * 当系统需要新增心理咨询师账户时，可以调用此方法进行初始化操作，
     * 避免手动创建账户的繁琐过程。
     *
     * 方法没有返回值，因为初始化账户的操作只需要执行，不需要返回任何数据。
     */
    @GetMapping("/init")
    public void initAccount(){
        psyConsultantAccountService.initAccount();
    }

    /**
     * 查询账户列表
     */
    @PreAuthorize("@ss.hasPermi('system:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(PsyConsultantAccount psyConsultantAccount)
    {
        startPage();
        List<PsyConsultantAccount> list = psyConsultantAccountService.selectPsyConsultantAccountList(psyConsultantAccount);
        return getDataTable(list);
    }

    /**
     * 导出账户列表
     */
    @PreAuthorize("@ss.hasPermi('system:account:export')")
    @Log(title = "账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PsyConsultantAccount psyConsultantAccount)
    {
        List<PsyConsultantAccount> list = psyConsultantAccountService.selectPsyConsultantAccountList(psyConsultantAccount);
        ExcelUtil<PsyConsultantAccount> util = new ExcelUtil<PsyConsultantAccount>(PsyConsultantAccount.class);
        util.exportExcel(response, list, "账户数据");
    }

    /**
     * 获取账户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:account:query')")
    @GetMapping(value = "/{consultantId}")
    public AjaxResult getInfo(@PathVariable("consultantId") Long consultantId)
    {
        PsyConsultantAccount account = psyConsultantAccountService.selectPsyConsultantAccountByConsultantId(consultantId);
        return AjaxResult.success(account);
    }

    /**
     * 新增账户
     */
    @PreAuthorize("@ss.hasPermi('system:account:add')")
    @Log(title = "账户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PsyConsultantAccount psyConsultantAccount)
    {
        return toAjax(psyConsultantAccountService.insertPsyConsultantAccount(psyConsultantAccount));
    }

    /**
     * 修改账户
     */
    @PreAuthorize("@ss.hasPermi('system:account:edit')")
    @Log(title = "账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PsyConsultantAccount psyConsultantAccount)
    {
        return toAjax(psyConsultantAccountService.updatePsyConsultantAccount(psyConsultantAccount));
    }

    /**
     * 删除账户
     */
    @PreAuthorize("@ss.hasPermi('system:account:remove')")
    @Log(title = "账户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{consultantIds}")
    public AjaxResult remove(@PathVariable Long[] consultantIds)
    {
        return toAjax(psyConsultantAccountService.deletePsyConsultantAccountByConsultantIds(consultantIds));
    }
}
