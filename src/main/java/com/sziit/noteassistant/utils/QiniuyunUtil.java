package com.sziit.noteassistant.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.*;
import com.sziit.noteassistant.http.QiniuyunCode;
import com.sziit.noteassistant.qiniuyun.QiniuyunUpload;
import com.sziit.noteassistant.qiniuyun.RegionCode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CGP 1577992659@qq.com
 */
@Component
public class QiniuyunUtil {

    /*private static String accessKey;
    @Value("${qiniuyun.accessKey}")
    public static void setAccessKey(String accessKey){
        QiniuyunUtil.accessKey = accessKey;
    }
    private static String secretKey;
    @Value("${qiniuyun.secretKey}")
    public static void setSecretKey(String secretKey){
        QiniuyunUtil.secretKey = secretKey;
    }
    private static String bucket;
    @Value("${qiniuyun.bucket}")
    public static void setBucket(String bucket){
        QiniuyunUtil.bucket = bucket;
    }

    private static String domain;
    @Value("${qiniuyun.domain}")
    public static void setDomain(String domain){
        QiniuyunUtil.domain = domain;
    }
    private static RegionCode regionCode;
    @Value("${qiniuyun.regionCode}")
    public static void setRegionCode(RegionCode regionCode){
        QiniuyunUtil.regionCode = regionCode;
    }
    //    QiniuyunUpload qiniuyunUpload = new QiniuyunUpload();
//    private static RegionCode regionCode = qiniuyunUpload.getRegionCode();*/

    private static final String ACCESS_KEY = QiniuyunUpload.accessKey;
    private static final String SECRET_KEY = QiniuyunUpload.secretKey;
    private static final String BUCKET = QiniuyunUpload.bucket;
    private static final String DOMAIN = QiniuyunUpload.domain;
    private static final RegionCode REGIONCODE = QiniuyunUpload.regionCode;
    private static Region region = null;

    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    static {
        switch (REGIONCODE){
            //华东
            case region0:
                region  = Region.region0();
                //华北
            case region1:
                region  = Region.region1();
                //华南
            case region2:
                region  = Region.region2();
                //北美
            case regionNa0:
                region  = Region.regionNa0();
                //东南亚
            case regionAs0:
                region  = Region.regionAs0();
                break;
        }
    }

    /**
     *
     * @return 上传参数
     */
    public static String getToken(){
        return auth.uploadToken(BUCKET,null,3600,new StringMap().put("insertOnly", 1)) ;
    }
    /**
     * 上传管理对象
     */
    private static UploadManager uploadManager = new UploadManager(new Configuration(region));

    /**
     * 本地上传
     * @param localFilePath
     * @return
     */
    public static Map<String ,Object> uploadPic(String localFilePath)  {
        Map<String,Object> map = new HashMap<String, Object>();
        System.out.println("token >>> "+ getToken());
        try {
            Response response = uploadManager.put(localFilePath, null, getToken());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            map.put("result", QiniuyunCode.NO_EXIST);
            if (!StringUtils.isNullOrEmpty(putRet.key)){
                String headImg = DOMAIN + putRet.key;
                map.put("headImage",headImg);
                map.put("result",QiniuyunCode.SUCCESS);
            }
        } catch ( QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return map;
    }
    /**
     *  base64方式上传
     * @param base64 ：图片编码
     * @return
     * @throws Exception
     */
    public static Map<String, Object> uploadPicByBase64(byte[] base64) throws Exception{
        String file64 = Base64.encodeToString(base64, 0);
        Integer length = base64.length;

        Map<String,Object> map = new HashMap<String, Object>();
        System.out.println("token >>> "+ getToken());
        try {
            Response response = uploadManager.put(file64, null, getToken());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            map.put("result", QiniuyunCode.NO_EXIST);
            if (!StringUtils.isNullOrEmpty(putRet.key)){
                String headImg = DOMAIN + putRet.key;
                map.put("headImage",headImg);
                map.put("result",QiniuyunCode.SUCCESS);
            }
        } catch ( QiniuException ex) {
           ex.printStackTrace();
        }
        return map;

        /*String dateStr = LocalDate.now().toString();
        // 将当前日期拼接在文件名称中
        fileName = dateStr + "/" + fileName;
        String url = "http://upload.qiniu.com/putb64/" + length + "/key/"+ UrlSafeBase64.encodeToString(fileName);
        //非华东空间需要根据注意事项 : 修改上传域名
        RequestBody requestBody = RequestBody.create(null, file64);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getToken())
                .post(requestBody).build();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        // 上传成功返回图片地址
        return response.isSuccessful()?(DOMAIN  + fileName):"Up Img Failed";*/
    }

    /**
     * 简单上传
     * @param localFilePath
     * @param fileName
     * @return
     * @throws QiniuException
     */
    public static String easyUpLoad(File localFilePath, String fileName) throws QiniuException {
        return intoResult(null,localFilePath,null, fileName,null);
    }

    /**
     * 字节数组上传
     * @param uploadBytes
     * @param fileName
     * @return
     * @throws QiniuException
     */
    public static String byteUpLoad(byte[] uploadBytes, String fileName) throws QiniuException {
        return intoResult(uploadBytes,null,null, fileName,null);
    }


    /**
     * 数据流上传
     * @param byteInputStream
     * @param fileName
     * @return
     * @throws QiniuException
     */
    public static String byteArrayInputStreamUpLoad(ByteArrayInputStream byteInputStream, String fileName) throws QiniuException {
        return intoResult(null,null,byteInputStream, fileName,null);
    }

    /**
     * 断点续上传
     * @param localFilePath
     * @param fileName
     * @return
     */
    public static String recorderUpLoad(File localFilePath, String fileName) {
        String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), BUCKET).toString();
        try {
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            return intoResult(null,localFilePath,null,fileName,fileRecorder);
        } catch (IOException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }
    private static String intoResult(byte[] uploadBytes, File localFilePath, ByteArrayInputStream byteInputStream, String fileName, FileRecorder fileRecorder) throws QiniuException {
        Configuration cfg = new Configuration(region);
        UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
//        String substring = fileName.substring(fileName.lastIndexOf("."));
        String key = "hlm/images/"+fileName;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        Response response = null;
        try {
            if(uploadBytes != null && localFilePath == null && byteInputStream == null){
                response = uploadManager.put(uploadBytes, key, upToken);
            }
            if(localFilePath != null && uploadBytes == null && byteInputStream == null){
                response = uploadManager.put(localFilePath, key, upToken);
            }
            if(byteInputStream != null  && uploadBytes == null && localFilePath == null){
                response = uploadManager.put(byteInputStream,key,upToken,null, null);
            }
            if(response == null){
                return "参数错误";
            }
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            throw new QiniuException(r);
        }
    }


}
