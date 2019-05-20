package com.shopping.core.controller;

import com.shopping.common.utils.FastDFSClient;
import entity.R;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;

    /**
     * 接受图片controller
     * MultipartFile 是接口不能接受数据 必须配置多媒体解析器 在springMVC.xml中
     *
     * @param file
     * @return
     */
    @RequestMapping("/uploadFile")
    public R uploadFile(MultipartFile file) {
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:fastDFS/fdfs_client.conf");
            //使用apach工具类获取文件扩展名
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            //上传图片 返回图片luj
            String path = fastDFSClient.uploadFile(file.getBytes(), ext, null);
            return new R(true,FILE_SERVER_URL + path);
        } catch (Exception e) {

            return new R(false, "上传失败");

        }
    }

}
