package com.boosters.mda.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.boosters.mda.entity.FileStorageEntity;

public interface IFileStorageService {

//	public void init();
	
	public List<FileStorageEntity> observeFiles(String userId);

	public boolean storeFiles(String userId, List<MultipartFile> files);
	
//	public Resource load(String filename);
	
//	public void deleteAll();
	
//	public Stream<Path> loadAll();
	
}
