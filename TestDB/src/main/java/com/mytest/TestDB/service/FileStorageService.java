package com.mytest.TestDB.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mytest.TestDB.entity.FileStorageEntity;
import com.mytest.TestDB.persistence.IFileStoragePersistence;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileStorageService implements IFileStorageService {
//	@Value("${file.dir}") // beans에서 가져온 어노테이션
//	private String fileDir;
//	autowired 붙여줬다. 혹시모르니까... 컨트롤러에서도 동일하게 autowired 넣어줬다. 이상 없는지 확인할 것...
	@Autowired
	private IFileStoragePersistence fPersistence;
	private final Path root = Paths.get("uploads");
	
	
	@Override
	public List<FileStorageEntity> observeFiles(String userid) {
		//stream을 지워도 리스트 똑같나? 확인하기. collect도.
		List<FileStorageEntity> models = fPersistence.findByUserId(userid).stream()
				.collect(Collectors.toList());
		return models;
	}

	
	@Override
	public boolean storeFiles(String userId, List<MultipartFile> files) throws IOException {
		//process 1
		List<FileStorageEntity> entities;
		entities = new ArrayList<>();
		for (MultipartFile file : files) {
			//process 2
			String origName = file.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String extension = origName.substring(origName.lastIndexOf("."));
			String savedName = uuid + extension;
			String savedPath = root + savedName;
			
			Files.copy(file.getInputStream(),
					this.root.resolve(file.getOriginalFilename()));
			
			
			//process 3
			
//			FileStorageModel model = FileStorageModel.builder()
//			.id("test"+file.getOriginalFilename())
//			.userId(userId)
//			.fName(file.getOriginalFilename())
//			.fUri(this.root.toString())
//			.time("savedtime")
//			.build();
			
			FileStorageEntity entity = FileStorageEntity.builder()
					.orgNm(origName)
					.savedNm(savedName)
					.savedPath(savedPath)
					.build();
			entities.add(entity);
			
		}
		fPersistence.saveAll(entities);
		return true;
	}

}
