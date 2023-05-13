package com.boosters.mda.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boosters.mda.dto.FileStorageDTO;
import com.boosters.mda.entity.FileStorageEntity;
import com.boosters.mda.persistence.IFileStorageRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileStorageService implements IFileStorageService {

	@Autowired
	private IFileStorageRepository fSrepo;
	
	private final Path root = Paths.get("uploads");
	
	/*
	 * Function: Serach Files
	 * Input: userId
	 * Output: List<FileStorageModel>
	 * Return: List<FileStorageModel>
	 * Process: 1) find files from persistence by userId
	 * 				...repository.findByUserId(userId) => List<Entity> entities
	 * 			2) return entities to Controller
	 * 				...entities.stream.map(new).collect(toList) => List<Model> models
	 * ETC: Update code later like using Adaptor(service-persistence)
	 */
	@Override
	public List<FileStorageEntity> observeFiles(String userId) {
		// Process 1) find files from repository by userId
		List<FileStorageEntity> entities = fSrepo.findByUserId(userId);
		// Process 2) return entities to Controller
		return entities;
		// return시 dto로 리턴할지 고민 중
	}
	
//	public FileStorageModel searchFiles(FileStorageModel model) {
//		// TODO 현재 storage 상태 보여주는 서비스
//		fSrepo.findAll(model.userid);
//		return null;
//	}
	
	/*
	 * Function: Search Files
	 * Input: { userId, MultipartFile }
	 * Output: boolean, List<FileStorageEntity> entities
	 * Return: boolean that file saved/unsaved 
	 * Process: 1) create entityList instead of Controller for saving
	 * 			...new List<Entity> entities
	 * 			1) save files to local drive by using forEach
	 * 			...forEach(file) { file.copy(localUri) ...
	 * 			3) create informations of each model
	 * 			...forEach{ model.builder.userId(userId).build...
	 * 			4) convert models to Entity entities and add them to entities
	 * 			...entities.add(model.toEntity); repository.save(entities)
	 */
	@Override
	public boolean storeFiles(String userId, List<MultipartFile> files) {
		
		// Process 1)
		List<FileStorageEntity> entities;
		
		try {
			
			// ...Process 1)
			entities = new ArrayList<>();
			
			// Process 2)
			for(MultipartFile file : files) {
				// ...Process 2)
				Files.copy(file.getInputStream(),
						this.root.resolve(file.getOriginalFilename())
						);
				
				// Process 3)
				FileStorageDTO dto = FileStorageDTO.builder()
											.id("test"+file.getOriginalFilename())
											.userId(userId)
											.fOrgName(file.getOriginalFilename())
											.fUUIDName("testUUID"+file.getOriginalFilename())
											.fUri(this.root.toString())
											.time("savedtime")
											.build();
				
				// Process 4)
				entities.add(FileStorageDTO.toEntity(dto));
				
			}
			
			fSrepo.saveAll(entities);
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
}
