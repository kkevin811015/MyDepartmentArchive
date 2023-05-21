package com.boosters.mda.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import com.boosters.mda.dto.FileStorageDTO;

public interface IFileStorageService {

//	public void init();
	
	public List<FileStorageDTO> observeFiles(String userId);

	public boolean storeFiles(String userId, List<MultipartFile> files);
	
	public ByteArrayResource loadBytesfromFile(String userId, String fileName) throws IOException;
	
	public UrlResource loadUrlResourcefromFile(String userId, String fileName) throws IOException;
	
	public Resource loadFile(String userId, String fileName);
	
	public List<Resource> loadFiles(String userId, List<String> fileNames);
	
//	public void deleteAll();
	
//	public Stream<Path> loadAll();
	
}
