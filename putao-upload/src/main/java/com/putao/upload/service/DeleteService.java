package com.putao.upload.service;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-24 10:03
 **/
@Service
public class DeleteService {

  @Autowired
  private FastFileStorageClient storageClient;

  public void deleteFileByFilePath(String file) {


    this.storageClient.deleteFile(file);

  }

}
