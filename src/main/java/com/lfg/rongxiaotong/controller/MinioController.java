package com.lfg.rongxiaotong.controller;

import com.lfg.rongxiaotong.domain.beans.ImageVo;
import cn.hutool.core.io.FileUtil;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang3.RandomStringUtils;
import com.lfg.rongxiaotong.utius.MinioUtilS;
import com.lfg.rongxiaotong.utius.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnails;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

import static cn.hutool.core.img.ImgUtil.getImageInputStream;

@RestController
@RequestMapping("/file")

public class MinioController {
    final long ONE_M = 5 * 1024 * 1024L;
    @Resource
    private MinioUtilS minioUtilS;
    @Value("${minio.host}")
    private String address;
    @Value("${minio.bucket}")
    private String bucketName;

    @PostMapping("/upload")
    public R<ImageVo> uploadFile(@RequestPart("file") MultipartFile multipartFile, HttpServletRequest request) {
        ImageVo imageVo = new ImageVo();
        String result = validFile(multipartFile);
        //判断图片是否符合条件
        if (!"success".equals(result)) {
            return uploadError(imageVo, multipartFile, result);
        }
        // 文件重命名
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        String filename = uuid + "-" + multipartFile.getOriginalFilename();
        try {
            //BufferedImage转MultipartFile
            BufferedImage bufferedImage = sizeImg(multipartFile);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            MultipartFile newMultipartFile = new MockMultipartFile("file", "filename.jpg", "image/jpeg", outputStream.toByteArray());
            //上传到minio文件吴福气
            String upload = minioUtilS.upload(newMultipartFile, filename);
            if (upload != null) {
                System.out.println("upload:" + upload);
                imageVo.setName(newMultipartFile.getOriginalFilename());
                imageVo.setUid(RandomStringUtils.randomAlphanumeric(8));
                imageVo.setUrl(upload);
                // 返回可访问地址
                return R.success(imageVo);
            }
            return uploadError(imageVo, newMultipartFile, "上传失败,情重试");
        } catch (Exception e) {
            return uploadError(imageVo, multipartFile, "上传失败,情重试");
        }

    }

    private R<ImageVo> uploadError(ImageVo imageVo, MultipartFile multipartFile, String message) {
        imageVo.setName(multipartFile.getOriginalFilename());
        imageVo.setUid(RandomStringUtils.randomAlphanumeric(8));
        return R.error(message);
    }

    /**
     * 有效文件
     * 校验文件
     *
     * @param multipartFile 多部分文件
     */
    private String validFile(MultipartFile multipartFile) {
        // 文件大小
        long fileSize = multipartFile.getSize();
        // 文件后缀
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        if (fileSize > ONE_M) {
            return "文件大小不能超过 5M";
        }
        if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp", "jiff").contains(fileSuffix)) {
            return "文件类型错误";
        }
        return "success";
    }

    private BufferedImage sizeImg(MultipartFile multipartFile) {
        try {
            // 读取图像
            BufferedImage originalImage = ImageIO.read(multipartFile.getInputStream());
            // 确保图像是正方形
            int size = Math.min(originalImage.getWidth(), originalImage.getHeight());
            BufferedImage squareImage = Thumbnails.of(originalImage)
                    .size(size, size)
                    .crop(Positions.CENTER)
                    .asBufferedImage();
            // 将裁剪后的图像保存到一个输出流
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ImageIO.write(squareImage, "jpg", output);
            return squareImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

