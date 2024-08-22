package com.ruoyi.system.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.exception.easyexcel.ExcelException;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.mapper.SysSupplierMapper;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 监听器,处理解析的excel数据
 */
@Slf4j
public class SupplierListener implements ReadListener<SysSupplier> {

    /**
     * 每隔10000条存储数据库，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 10000;
    /**
     * 表头数据
     */
    private final String[] HEAD_LIST = {
            "标签",
            "性质",
            "国家",
            "省份",
            "市",
            "地区",
            "详细地址",
            "经营类别",
            "主营类别",
            "注册编号",
            "在华注册编号",
            "企业名称",
            "企业名称（英文）",
            "企业名称（本国语）",
            "注册时间",
            "注册时间有效期",
            "是否删除",
            "法人",
            "法人电话",
            "法人电子邮件",
            "负责人",
            "负责人电话",
            "负责人电子邮件",
            "分类",
            "数据来源"
    };
    /**
     * 表头异常信息提示
     */
    private final String HEAD_INFO = "您上传的文件格式与模板格式不一致，请检查后重新上传";
    /**
     * 表头异常标识，true表示无异常，false表示出现异常
     */
    private Boolean flag = true;
    /**
     * 记录异常信息
     */
    private final List<String> arrayList = new ArrayList<>();
    /**
     * 缓存的数据
     */
    private List<SysSupplier> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 供应商mapper层
     */
    private SysSupplierMapper sysSupplierMapper;

    /**
     * 个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param sysSupplierMapper
     */
    public SupplierListener(SysSupplierMapper sysSupplierMapper) {
        this.sysSupplierMapper = sysSupplierMapper;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(SysSupplier data, AnalysisContext context) {
        // 当表头数据无误才会解析数据
        if (flag) {
            log.info("解析到一条数据:{}", JSON.toJSONString(data));
            // 数据库中不能为空的字段
            if (data.getLabel() == null) {
                int row = context.readRowHolder().getRowIndex() + 1;
                arrayList.add("第" + row + "行" + "标签列数据异常");
            } else if (data.getCountry() == null) {
                int row = context.readRowHolder().getRowIndex() + 1;
                arrayList.add("第" + row + "行" + "国家列数据异常");
            } else if (data.getRegistrationNo() == null) {
                int row = context.readRowHolder().getRowIndex() + 1;
                arrayList.add("第" + row + "行" + "注册编号列数据异常");
            } else if (data.getLegalPerson() == null) {
                int row = context.readRowHolder().getRowIndex() + 1;
                arrayList.add("第" + row + "行" + "法人列数据异常");
            } else if (data.getLegalPersonTelephone() == null) {
                int row = context.readRowHolder().getRowIndex() + 1;
                arrayList.add("第" + row + "行" + "法人电话列数据异常");
            } else {
                // 设置供应商UUID
                data.setSupplierId(UUID.randomUUID().toString());
                // 设置入驻时间
                data.setEntryDate(new Date());
                cachedDataList.add(data);
                // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
                if (cachedDataList.size() >= BATCH_COUNT) {
                    saveData();
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        if (!cachedDataList.isEmpty()) {
            saveData();
        }
        log.info("所有数据解析完成！");
        // 存在异常数据,抛出
        if (!arrayList.isEmpty()) {
            arrayList.forEach((list) -> {
                log.error("{}", list);
            });
            throw new ExcelException(arrayList +"其余数据成功导入");
        }
        // 存在表头异常
        if (!flag) {
            throw new ExcelException(HEAD_INFO);
        }

    }

    /**
     * 这里会一行行的返回头
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
        if (headMap.size() != HEAD_LIST.length) {
            log.info("表头长度异常，模板表头长度：{}，上传表头长度：{}", HEAD_LIST.length, headMap.size());
            // 设置标识，后续抛出对应异常
            flag = false;
            return;
        }
        for (int i = 0; i < HEAD_LIST.length; i++) {
            if (!headMap.get(i).getStringValue().equals(HEAD_LIST[i])) {
                log.info("上传表头字段：“{}”与模板表头字段：“{}”不一致", headMap.get(i).getStringValue(), HEAD_LIST[i]);
                // 设置标识，后续抛出异常
                flag = false;
                return;
            }
        }
    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
        }
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        try {
            sysSupplierMapper.saveSysSupplier(cachedDataList);
        } catch (Exception e) {
            log.error("存储数据库失败:{}", e.getMessage());
            arrayList.add("存储数据库失败!");
        }
        log.info("存储数据库成功！");
    }
}
