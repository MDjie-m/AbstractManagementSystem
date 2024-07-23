package com.ruoyi.system.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.exception.easyexcel.ExcelNullException;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.system.domain.SysSupplier;
import com.ruoyi.system.service.ISysSupplierService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监听器,处理解析的excel数据
 */
@Slf4j
public class SupplierListener implements ReadListener<SysSupplier> {

    /**
     * 每隔100000条存储数据库，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100000;
    /**
     * 记录数据库中不能为空的数据的行号和列信息
     */
    private final Map<Integer, String> cellData = new HashMap<>();
    /**
     * 缓存的数据
     */
    private List<SysSupplier> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private ISysSupplierService iSysSupplierService;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param iSysSupplierService
     */
    public SupplierListener(ISysSupplierService iSysSupplierService) {
        this.iSysSupplierService = iSysSupplierService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(SysSupplier data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        // 数据库中不能为空的字段
        if (data.getLabel() == null) {
            Integer row = context.readRowHolder().getRowIndex();
            cellData.put(row + 1, "标签列异常");
        } else if (data.getCountry() == null) {
            Integer row = context.readRowHolder().getRowIndex();
            cellData.put(row + 1, "国家列异常");
        } else if (data.getRegistrationNo() == null) {
            Integer row = context.readRowHolder().getRowIndex();
            cellData.put(row + 1, "注册编号列异常");
        } else {
            // 设置供应商UUID
            data.setSupplierId(UUID.randomUUID().toString());
            cachedDataList.add(data);
            // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
            if (cachedDataList.size() >= BATCH_COUNT) {
                saveData();
                // 存储完成清理 list
                cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
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
        if (!cellData.isEmpty()) {
            cellData.forEach((k, v) -> {
                log.error("异常数据在第{}行，{}", k, v);
            });
            throw new ExcelNullException(cellData);
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
        // 如果想转成成 Map<Integer,String>
        // 方案1： 不要implements ReadListener 而是 extends AnalysisEventListener
        // 方案2： 调用 ConverterUtils.convertToStringMap(headMap, context) 自动会转换
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
            iSysSupplierService.saveSysSupplier(cachedDataList);
        } catch (Exception e) {
            log.error("存储数据库失败:{}", e.getMessage());
        }
        log.info("存储数据库成功！");
    }
}
