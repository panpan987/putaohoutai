package com.putao.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-14 14:59
 **/
@Service
public class UploadService {
  //前台已经校验了
  //private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png");

  private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

  @Autowired
  private FastFileStorageClient storageClient;

  public String uploadImage(MultipartFile file) {

    String originalFilename = file.getOriginalFilename();
    //校验文件类型
//    String contentType = file.getContentType();
//    if (!CONTENT_TYPES.contains(contentType)) {
//      LOGGER.info("文件类型不合法: {}", originalFilename);
//      return null;
//    }

    try {
      //校验文件内容
      BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
      if (bufferedImage == null) {
        LOGGER.info("文件内容不合法: {}", originalFilename);
        return null;
      }

      //保存到服务器
      //file.transferTo(new File("D:\\pthoutai\\image\\" + originalFilename));
      String ext = StringUtils.substringAfterLast(originalFilename, ".");
      StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);


      //返回url,进行回显
      //return "http://image.putao.com/" + originalFilename;
      return "http://image.pilibili.cn/" + storePath.getFullPath();
    } catch (IOException e) {
      LOGGER.info("服务器内部错误: " + originalFilename);
      e.printStackTrace();
    }
    return null;
  }
}
