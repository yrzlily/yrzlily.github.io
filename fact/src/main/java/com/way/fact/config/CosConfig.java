package com.way.fact.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 腾讯云对象储存配置
 * @author yrz
 */
@Configuration
public class CosConfig {

    private static String SecretId = "AKIDuiOSRGGOp6CzhY6m9JJDwYzGMJ6ltk3A";
    private static String SecretKey = "FOZZO2UlbNzPglfPyMbfpqjcLtFRCxb4";
    private static String bucket = "ap-shanghai";
    private static COSCredentials cred = new BasicCOSCredentials(SecretId , SecretKey);
    private static ClientConfig clientConfig = new ClientConfig(new Region(bucket));

    /**
     * 文件上传到COS
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    public String cosFileUpload(MultipartFile file , HttpServletRequest request) throws IOException {
        COSClient client = new COSClient(cred , clientConfig);

        //重命名文件
        String finType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        UUID uid = UUID.randomUUID();

        //上传到本地
        File upload = new File(request.getSession().getServletContext().getRealPath("/images/upload/") + uid + finType);
        if(!upload.getParentFile().exists()){
            upload.getParentFile().mkdirs();
        }
        FileOutputStream out = new FileOutputStream(upload);
        out.write(file.getBytes());
        out.flush();
        out.close();

        //上传到云服务
        String key = upload.getName();
        String bucketName = "yrzssr-1256893303";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key , upload);

        //返回结果
        PutObjectResult putObjectResult = client.putObject(putObjectRequest);
        String path = "https://yrzssr-1256893303.cossh.myqcloud.com";

        //关闭连接
        client.shutdown();

        return path + "/" + putObjectRequest.getKey();
    }

}
