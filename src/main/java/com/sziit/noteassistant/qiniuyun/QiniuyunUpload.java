package com.sziit.noteassistant.qiniuyun;

import com.qiniu.storage.Region;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/3  0:31
 */
@Component
public class QiniuyunUpload {
    //访问密钥
    public static String accessKey;
    //加密密钥
    public static String secretKey;
    //空间名称
    public static String bucket;
//    //上传文件名
//    public static String key;
    //上传空间域名
    public static String domain;
    //区域
    public static RegionCode regionCode;

    @Value("${qiniuyun.ACCESS_KEY}")
    private String tempAccessKey;
    @Value("${qiniuyun.SECRET_KEY}")
    private String tempSecretKey;
    @Value("${qiniuyun.BUCKET}")
    private String tempBucket;
    @Value("${qiniuyun.DOMAIN}")
    private String tempDomain;
    @Value("${qiniuyun.REGIONCODE}")
    private String  tempRegion;

    @PostConstruct
    public void setAccessKey() {
        accessKey = this.tempAccessKey;
    }
    @PostConstruct
    public void setSecretKey() {
        secretKey = this.tempSecretKey;
    }
    @PostConstruct
    public void setBucket() {
        bucket = this.tempBucket;
    }
    @PostConstruct
    public void setDomain() {
        domain = this.tempDomain;
    }
    @PostConstruct
    public void setRegionCode() {
        regionCode = RegionCode.valueOf(this.tempRegion);
    }

   /* public QiniuyunUpload(String accessKey, String secretKey, String bucket) {
        this.tempAccessKey = accessKey;
        this.tempSecretKey = secretKey;
        this.tempBucket = bucket;
        Auth auth = Auth.create(accessKey,secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        this.token = auth.uploadToken(bucket,null,expireSeconds,putPolicy);
    }*/
}
