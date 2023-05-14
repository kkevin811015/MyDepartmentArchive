package com.boosters.mda.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.boosters.mda.dto.FileStorageDTO;

public interface IFileStorageService {

//	public void init();
	
	public List<FileStorageDTO> observeFiles(String userId);

	public boolean storeFiles(String userId, List<MultipartFile> files);
	
//	public Resource load(String filename);
	
//	public void deleteAll();
	
//	public Stream<Path> loadAll();
	
}
