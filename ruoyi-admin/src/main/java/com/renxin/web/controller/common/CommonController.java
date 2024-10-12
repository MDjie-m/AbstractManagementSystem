package com.renxin.web.controller.common;

import cn.hutool.core.util.StrUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.utils.Jackson;
import com.renxin.common.annotation.RateLimiter;
import com.renxin.common.config.COSConfig;
import com.renxin.common.config.RuoYiConfig;
import com.renxin.common.constant.Constants;
import com.renxin.common.core.domain.AjaxResult;
import com.renxin.common.utils.IDhelper;
import com.renxin.common.utils.StringUtils;
import com.renxin.common.utils.cos.COSClientFactory;
import com.renxin.common.utils.file.FileUtils;
import com.renxin.common.utils.spring.SpringUtils;
import com.renxin.framework.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.TreeMap;

import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Policy;
import com.tencent.cloud.Response;
import com.tencent.cloud.Statement;

/**
 * 通用请求处理
 * 
 * @author renxin
 */
@RestController
@RequestMapping("/common")
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    private static final String FILE_DELIMETER = ",";

    private  static COSClient cosClient;
    private  static COSConfig cosConfig;

    @GetMapping("/getCredential")
    public AjaxResult getCredential(HttpServletRequest request) throws Exception
    {
        String module = request.getHeader("module");
        String bucketName = COSClientFactory.getBucketName(module);
        
        cosConfig = SpringUtils.getBean(COSConfig.class);
        TreeMap<String, Object> config = new TreeMap<String, Object>();
       // try {
            //这里的 SecretId 和 SecretKey 代表了用于申请临时密钥的永久身份（主账号、子账号等），子账号需要具有操作存储桶的权限。
            String secretId = cosConfig.getSecretId();//用户的 SecretId，建议使用子账号密钥，授权遵循最小权限指引，降低使用风险。子账号密钥获取可参见 https://cloud.tencent.com/document/product/598/37140
            String secretKey = cosConfig.getSecretKey();//用户的 SecretKey，建议使用子账号密钥，授权遵循最小权限指引，降低使用风险。子账号密钥获取可参见 https://cloud.tencent.com/document/product/598/37140
            // 替换为您的云 api 密钥 SecretId
            config.put("secretId", secretId);
            // 替换为您的云 api 密钥 SecretKey
            config.put("secretKey", secretKey);

            // 初始化 policy
            Policy policy = new Policy();

            // 设置域名:
            // 如果您使用了腾讯云 cvm，可以设置内部域名
            //config.put("host", "sts.internal.tencentcloudapi.com");

            // 临时密钥有效时长，单位是秒，默认 1800 秒，目前主账号最长 2 小时（即 7200 秒），子账号最长 36 小时（即 129600）秒
            config.put("durationSeconds", 3600);
            // 换成您的 bucket
            config.put("bucket", bucketName);
            // 换成 bucket 所在地区
            config.put("region", "ap-beijing");

            // 开始构建一条 statement
            Statement statement = new Statement();
            // 声明设置的结果是允许操作
            statement.setEffect("allow");
            /**
             * 密钥的权限列表。必须在这里指定本次临时密钥所需要的权限。
             * 权限列表请参见 https://cloud.tencent.com/document/product/436/31923
             * 规则为 {project}:{interfaceName}
             * project : 产品缩写  cos相关授权为值为cos,数据万象(数据处理)相关授权值为ci
             * 授权所有接口用*表示，例如 cos:*,ci:*
             * 添加一批操作权限 :
             */
            statement.addActions(new String[]{
                    "name/cos:PutObject",
                    // 表单上传、小程序上传
                   /* "cos:PostObject",
                    // 分块上传
                    "cos:InitiateMultipartUpload",
                    "cos:ListMultipartUploads",
                    "cos:ListParts",
                    "cos:UploadPart",
                    "cos:CompleteMultipartUpload",
                    // 处理相关接口一般为数据万象产品 权限中以ci开头
                    // 创建媒体处理任务
                    "ci:CreateMediaJobs",
                    // 文件压缩
                    "ci:CreateFileProcessJobs"*/
            });

            /**
             * 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径
             * 资源表达式规则分对象存储(cos)和数据万象(ci)两种
             * 数据处理、审核相关接口需要授予ci资源权限
             *  cos : qcs::cos:{region}:uid/{appid}:{bucket}/{path}
             *  ci  : qcs::ci:{region}:uid/{appid}:bucket/{bucket}/{path}
             * 列举几种典型的{path}授权场景：
             * 1、允许访问所有对象："*"
             * 2、允许访问指定的对象："a/a1.txt", "b/b1.txt"
             * 3、允许访问指定前缀的对象："a*", "a/*", "b/*"
             *  如果填写了“*”，将允许用户访问所有资源；除非业务需要，否则请按照最小权限原则授予用户相应的访问权限范围。
             *
             * 示例：授权examplebucket-1250000000 bucket目录下的所有资源给cos和ci 授权两条Resource
             */
            statement.addResource("*");
           /* statement.addResources(new String[]{
                    "qcs::cos:ap-chongqing:uid/1250000000:examplebucket-1250000000/*",
                    "qcs::ci:ap-chongqing:uid/1250000000:bucket/examplebucket-1250000000/*"});*/

            // 把一条 statement 添加到 policy
            // 可以添加多条
            policy.addStatement(statement);
            // 将 Policy 示例转化成 String，可以使用任何 json 转化方式，这里是本 SDK 自带的推荐方式
            config.put("policy", Jackson.toJsonPrettyString(policy));

            
            Response response = CosStsClient.getCredential(config);
            System.out.println(response.credentials.tmpSecretId);
            System.out.println(response.credentials.tmpSecretKey);
            System.out.println(response.credentials.sessionToken);
        /*} catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("no valid secret !");
        }*/
        
        return AjaxResult.success(bucketName + "::" + cosConfig.getRegion(), response);
    }

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    @RateLimiter
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    @RateLimiter
    public AjaxResult uploadFile(MultipartFile file, HttpServletRequest request) throws Exception
    {
        String module = request.getHeader("module");
        String type = request.getHeader("type");

        UploadResult upload = null;
        InputStream inputStream = null;
        try
        {
            // 上传文件路径
//            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
//            String fileName = FileUploadUtils.upload(filePath, file);
            String fileName = StrUtil.format("{}_{}_{}", type, IDhelper.getNextId(), file.getOriginalFilename());
//            String url = serverConfig.getUrl() + fileName;
            //  调用文件服务器方法，实现文件上传改写
            inputStream = file.getInputStream();
            upload = COSClientFactory.upload(inputStream, fileName, module);
            String key = upload.getKey();
            String url = COSClientFactory.getObjUrl(key, module);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
//            ajax.put("fileName", fileName);
//            ajax.put("newFileName", FileUtils.getName(fileName));
//            ajax.put("originalFilename", file.getOriginalFilename());
            ajax.put("fileName", fileName);
            ajax.put("newFileName", key);
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传文件删除
     */
    @PostMapping("/upload/delete")
    @RateLimiter
    public AjaxResult deleteFile(@RequestParam @NotNull String fileKey, @RequestParam @NotNull String module) throws Exception {
        try {
            COSClientFactory.deleteObject(module, fileKey);
            return AjaxResult.success("删除" + fileKey + "成功");
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    @RateLimiter
    public AjaxResult uploadFiles(List<MultipartFile> files, HttpServletRequest request) throws Exception
    {
        try
        {
            String module = request.getHeader("module");
            String type = request.getHeader("type");

            // 上传文件路径
//            String filePath = RuoYiConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files)
            {
                // 上传并返回新文件名称
//                String fileName = FileUploadUtils.upload(filePath, file);
                String fileName = StrUtil.format("{}_{}_{}", type, IDhelper.getNextId(), file.getOriginalFilename());
//                String url = serverConfig.getUrl() + fileName;
                //  调用ods接口
                InputStream inputStream = file.getInputStream();
                UploadResult upload = COSClientFactory.upload(inputStream, fileName, module);
                String key = upload.getKey();
                String url = COSClientFactory.getObjUrl(key, module);
                urls.add(url);
                fileNames.add(url);
                newFileNames.add(key);
                originalFilenames.add(file.getOriginalFilename());
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    @RateLimiter
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
}
