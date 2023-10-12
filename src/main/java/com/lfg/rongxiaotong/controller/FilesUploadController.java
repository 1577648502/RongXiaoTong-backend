package com.lfg.rongxiaotong.controller;

import com.lfg.rongxiaotong.utius.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
public class FilesUploadController {

    @Value("${SavePath.ProfilePhoto}")
    private String ProfilePhotoSavePath;   //图标图片存储路径
    @Value("${SavePath.ProfilePhotoMapper}")
    private String ProfilePhotoMapperPath;   //图标映射路径

    /**
     * 头像上传
     *
     * @param fileUpload 图片资源
     * @return 图映射的虚拟访问路径
     */
    @PostMapping("/upload")
    public R<String> profilePhotoUpload(@RequestParam("image") MultipartFile fileUpload) {
        if (fileUpload.isEmpty()){
            return R.error("上传失败，请选择文件");
        }
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名。也可以在这里添加判断语句，规定特定格式的图片才能上传，否则拒绝保存。
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //为了避免发生图片替换，这里使用了文件名重新生成
        fileName = UUID.randomUUID() + suffixName;
        try {
            //将图片保存到文件夹里
            fileUpload.transferTo(new File(ProfilePhotoSavePath + fileName));
            return R.success(ProfilePhotoMapperPath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("上传失败，请重试");
        }
    }

}
