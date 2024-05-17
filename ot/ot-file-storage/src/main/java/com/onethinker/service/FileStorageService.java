package com.onethinker.service;

import cn.hutool.core.util.StrUtil;
import com.onethinker.domain.FileInfo;
import com.onethinker.config.FileStorageProperties;
import com.onethinker.domain.FileRelation;
import com.onethinker.mapper.FileMapper;
import com.onethinker.mapper.FileRelationMapper;
import com.onethinker.util.Tools;
import com.onethinker.platform.FileStorage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yangyouqi
 * @date 2024/5/14
 * 用来处理文件存储，对接多个平台
 */
@Slf4j
@Getter
@Setter
public class FileStorageService {

    private FileStorageProperties properties;

    private CopyOnWriteArrayList<FileStorage> fileStorageList;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private FileRelationMapper relationMapper;

    /**
     * 获取默认的存储平台
     */
    public <T extends FileStorage> T getFileStorage() {
        return this.getFileStorage(properties.getDefaultPlatform());
    }

    /**
     * 获取对应的存储平台
     */
    public <T extends FileStorage> T getFileStorage(String platform) {
        for (FileStorage fileStorage : fileStorageList) {
            if (fileStorage.getPlatform().equals(platform)) {
                return Tools.cast(fileStorage);
            }
        }
        return null;
    }

    /**
     * 获取对应的存储平台，如果存储平台不存在则抛出异常
     */
    public <T extends FileStorage> T getFileStorageVerify(String platform) {
        T fileStorage = this.getFileStorage(platform);
        if (fileStorage == null)
            throw new RuntimeException(StrUtil.format("没有找到对应的存储平台！platform:{}", platform));
        return fileStorage;
    }

    /**
     * 保存实体
     *
     * @param fileRelation
     * @param fileInfo
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveFileInfo(FileRelation fileRelation, FileInfo fileInfo) {
        fileMapper.insert(fileInfo);
        relationMapper.insert(fileRelation);
    }
}
