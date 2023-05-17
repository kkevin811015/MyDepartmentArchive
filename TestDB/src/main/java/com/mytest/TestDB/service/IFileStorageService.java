package com.mytest.TestDB.service;

import java.io.IOException;
import java.util.List;

import com.mytest.TestDB.entity.FileStorageEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
	public List <FileStorageEntity> observeFiles(String userid);
	public boolean storeFiles(String userId, List<MultipartFile> files)throws IOException;
}
