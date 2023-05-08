package com.boosters.mda.service;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.boosters.mda.dto.FileStorageDTO;
import com.boosters.mda.entity.FileStorageEntity;
import com.boosters.mda.model.FileStorageModel;

public interface IFileStorageService {

//	public void init();
	
	public List<FileStorageModel> observeFiles(String userId);

	public boolean storeFiles(String userId, List<MultipartFile> files);
	
//	public Resource load(String filename);
	
//	public void deleteAll();
	
//	public Stream<Path> loadAll();
	
}
