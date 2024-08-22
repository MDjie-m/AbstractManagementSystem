package com.renxin.app;

import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.UploadResult;
import com.renxin.RuoYiApplication;
import com.renxin.common.utils.cos.COSClientFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RuoYiApplication.class)
@Ignore
public class OssTest {

    @Test
    public void deleteBucket() {
        String bucket = "backup-1316267898";
        COSClientFactory.deleteBucket(bucket);
        System.out.println("删除成功");
        // COSBucket [name=zx-1316267898, creationDate=null, location=null, owner=null]
        // COSBucket [name=backup-1316267898, creationDate=null, location=null, owner=null]
    }

    @Test
    public void deleteObject() {
        COSClientFactory.deleteObject("backup", "psychology-test-202308281446.sql.gz");
        System.out.println("删除成功");
        // COSBucket [name=zx-1316267898, creationDate=null, location=null, owner=null]
        // COSBucket [name=backup-1316267898, creationDate=null, location=null, owner=null]
    }

    @Test
    public void createBucket() {
        String bucket = "backup";
        Bucket bucket1 = COSClientFactory.createBucket(bucket);
        System.out.println(bucket1);
        // COSBucket [name=zx-1316267898, creationDate=null, location=null, owner=null]
        // COSBucket [name=backup-1316267898, creationDate=null, location=null, owner=null]
    }

    @Test
    public void testUpload() {
        // String directoryPath = RuoYiConfig.getBackupPath();
        String directoryPath = "/Users/qinjin/isoft/psychology/D:/renxin/uploadPath/backup";
        try {
            // 获取目录下的所有文件
            Path directory = Paths.get(directoryPath);
            List<Path> files = Files.list(directory).collect(Collectors.toList());

            // 遍历并打印文件名
            for (Path filePath : files) {
                String fileName = filePath.getFileName().toString();
                if (fileName.contains(".gz")) {
                    File file = filePath.toFile();
                    InputStream inputStream = new FileInputStream(file);
                    //  调用文件服务器方法，实现文件上传改写
                    UploadResult upload = COSClientFactory.upload(inputStream, fileName, "backup");
                    String key = upload.getKey();
                    String url = COSClientFactory.getObjUrl(key, "backup");
                    boolean delete = file.delete();
                    System.out.println("已成功备份远程MySQL数据库::::::" + url);
                    // 已成功备份远程MySQL数据库::::::https://backup-1316267898.cos.ap-beijing.myqcloud.com/psychology-test-202308281446.sql.gz
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
