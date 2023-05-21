package com.boosters.mda.service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
	
//	@Value("${file.dir}")
//	private final String root;
	private final Path root = Paths.get("D:/SWdevelop/workspace/Boosters/TESTCONTAINER");
	
	/*
	 * Function: Observe Files
	 * Input: userId
	 * Output: List<FileStorageModel>
	 * Return: List<FileStorageModel>
	 * Process: 1) find files from persistence by userId
	 * 				...repository.findByUserId(userId) => List<Entity> entities
	 * 			2) return entities to Controller
	 * 				...entities.stream.map(new).collect(toList) => List<DTO> dtos
	 * ETC: Update code later like using Adaptor(service-persistence)
	 */
	@Override
	public List<FileStorageDTO> observeFiles(String userId) {
		// Process 1) find files from repository by userId
		List<FileStorageEntity> entities = fSrepo.findByUserId(userId);
		// Process 2) create dtos from entities
		List<FileStorageDTO> dtos = entities.stream()
				.map(FileStorageDTO::new)
				.collect(Collectors.toList());
		// Process 2) return entities to Controller
		return dtos;
	}
	
	/*
	 * Function: Store Files
	 * Input: { userId, MultipartFile }
	 * Output: boolean, List<FileStorageEntity> entities
	 * Return: boolean that file saved/unsaved 
	 * Process: 1) create entityList instead of Controller for saving
	 * 			...new List<Entity> entities
	 * 			2) create informations of each file
	 * 			...forEach{ entity.builder.userId(userId)....build...
	 * 			3) save files to local drive by using forEach
	 * 			...forEach(file) { file.copy(localUri) ...
	 * 			4) add entity to entities
	 * 			...entities.add(entity); repository.saveAll(entities)
	 */
	@Override
	public boolean storeFiles(String userId, List<MultipartFile> files) {
		
		// local variables
		UUID uuid = UUID.randomUUID();
		
		// Process 1)
		List<FileStorageEntity> entities;
		
		try {
			
			// ...Process 1)
			entities = new ArrayList<>();
			
			// Process 2)
			for(MultipartFile file : files) {
				// file information variables
				// Who specified like userId
				// When automatically created by JpaAuditing
				// Where specified like this.root
				// What 1 ContentType
				String fileContentType = file.getContentType();
				// What 2 fileName
				String fileName = file.getOriginalFilename();
				// What 3 UUID-fileName
				String uuidFileName = uuid.toString() + "-" + fileName;
				// What 4 file's extension
				String fileExtension = fileName.substring(fileName.lastIndexOf("."));
				
				// ...Process 2)
				FileStorageDTO dto = FileStorageDTO.builder()
											.userId(userId)
											.fileName(file.getOriginalFilename())
											.fileUri(this.root.toString())
											.build();
				// ...Process 3)
				Files.copy(file.getInputStream(),
						this.root.resolve(uuid.toString()+"-"+file.getOriginalFilename())
						);
				
				// Process 3)
				FileStorageEntity entity = FileStorageEntity.builder()
											.userId(userId) // Who
											// When(automatically created by JpaAuditing)
											.savedUri(this.root.toString()) // Where
											.contentType(fileContentType)	// What 1
											.fileUUIDName(uuidFileName) // What 2
											.fileOrgName(fileName) // What 3 
											.extension(fileExtension) // What 4
											.build();
				// Process 4)
				entities.add(entity);
			}
			// ...Process 4)
			fSrepo.saveAll(entities);
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	/*
	 * Function: Load File
	 * Input: { userId, fileName }
	 * Output: Resource file, List<FileStorageEntity> entities
	 * Return: Resource files
	 * Process: 
	 */
	@Override
	public Resource loadFile(String userId, String fileName) {
		
		return null;
	}

	/*
	 * Function: Load Files
	 * Input: { userId, fileNames }
	 * Output: List<Resource> files, List<FileStorageEntity> entities
	 * Return: List<Resource> files
	 * Process: 
	 */
	@Override
	public List<Resource> loadFiles(String userId, List<String> fileNames) {
		
//		List<FileStorageEntity> entities = fSrepo.findByUserIdAndFOrgName(userId)
		
		return null;
	}

	/*
	 * Function: Load Bytes from Files
	 * Input: { userId, fileName }
	 * Output: ByteArray fileBytes, FileStorageEntity entity
	 * Return: ByteArray fileBytes
	 * Process: 1) search Entity by userId and fileName
	 * 			2) search real file from UUID-fileName which specified by entity
	 * 				2-1) If that file exists, perform 3)
	 * 				2-2) If not exist, return null
	 * 			3) return bytes of searched file
	 */
	@Override
	public ByteArrayResource loadBytesfromFile(String userId, String fileName) throws IOException {
		
		// Process 1) search Entity
		FileStorageEntity entity = fSrepo.findByUserIdAndFileOrgName(userId, fileName);
		
		// Process 2) search real(UUID) file
		File rootDir = new File(entity.getSavedUri());
		
		File[] file = rootDir.listFiles( new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains(entity.getFileUUIDName());
			}
		});
		
		byte[] fileByte = null;
		
		for (File f : file) {
			System.out.println("Searched file:" + f.getName() 
								+ "\nPath: " + f.toPath());
//								+ "\nURI: " + f.toURI());
			
			fileByte = Files.readAllBytes(f.toPath());
		}
		
		return new ByteArrayResource(fileByte);
	}

	/*
	 * Function: Load UrlResource from Files
	 * Input: { userId, fileName }
	 * Output: ByteArray fileBytes, FileStorageEntity entity
	 * Return: ByteArray fileBytes
	 * Process: 1) search Entity by userId and fileName
	 * 			2) search real file from UUID-fileName which specified by entity
	 * 				2-1) If that file exists, perform 3)
	 * 				2-2) If not exist, return null
	 * 			3) return bytes of searched file
	 */
	@Override
	public UrlResource loadUrlResourcefromFile(String userId, String fileName) throws IOException {
		
		// Process 1) search Entity
		FileStorageEntity entity = fSrepo.findByUserIdAndFileOrgName(userId, fileName);
				
		// Process 2) search real(UUID) file
		File rootDir = new File(entity.getSavedUri());
				
		File[] file = rootDir.listFiles( new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains(entity.getFileUUIDName());
				}
			});
				
		UrlResource resource = null;
				
		for (File f : file) {
			System.out.println("Searched file:" + f.getName() 
//									+ "\nPath: " + f.toPath());
									+ "\nURI: " + f.toURI());
					
			resource = new UrlResource(f.toURI());
		}
				
		return resource;
	}
	
}
