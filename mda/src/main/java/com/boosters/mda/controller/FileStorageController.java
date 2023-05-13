package com.boosters.mda.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.boosters.mda.dto.FileStorageDTO;
import com.boosters.mda.dto.ResponseDTO;
import com.boosters.mda.entity.FileStorageEntity;
import com.boosters.mda.service.FileStorageService;

@RestController
@RequestMapping("/storage/{user}/*")
public class FileStorageController {
	
	@Autowired
	FileStorageService fStorageService;
	
	ModelAndView mnv;
	
	/*
	 * Function: File List Display Controller
	 * Method: Get
	 * Request: null
	 * Response: DTO: Files{ File{ fname, uri, userid, time }, ...iteration }
	 * Return: ModelAndView("storage[.html]")
	 * Process: ...service.StorageManagement.FilesSearch(Model)
	 */
	// Method Set
	@GetMapping
	public ModelAndView FileListDisplayController(@PathVariable("user") String user) {
		// View Set:
		mnv = new ModelAndView("storage");
		
		// Process Set:
		// Response Load From Service
		// 변경 예정
		List<FileStorageEntity>entities = fStorageService.observeFiles(user);
		List<FileStorageDTO> dtos = entities.stream()
											.map(FileStorageDTO::new)
											.collect(Collectors.toList());
		
		ResponseDTO<FileStorageDTO> filesInfo = ResponseDTO.<FileStorageDTO>builder()
															.data(dtos)
															.build();
		
		// Response Set to ModelAndView
		mnv.addObject("files", filesInfo); //Files{ FileInfo{}, ..., }
		
		return mnv; // Return Set
	}
	
	/*
	 * Function: File Upload Controller
	 * Method: Post
	 * Request: Form: MultipartFile, userid
	 * Response: None
	 * Return: Message: "File Uploaded"
	 * Process: ...service.StorageManagement.FilesStore(MultipartFile, userid)
	 */
	// Method Set
	@PostMapping("/upload")
	public ModelAndView FileUploadController( // Request Set
			@PathVariable("user") String userId,
			@RequestParam("files") List<MultipartFile> files
			) {
		
		// Process Set: FilesStore
		boolean filesave = fStorageService.storeFiles(userId, files);
		
		
		if (filesave) {
			// Return Set: message "File Uploaded"
			mnv.setViewName("redirect:/storage/"+userId+"/");
		}else {
			// Return Set: message "File Upload Failed"
			mnv.setViewName("redirect:/");
		}
		return mnv;
	}
	
	
	// !! Backup !!
	/*
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> FileUploadController(
			@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			fStoragesService.save(file);
			
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		}catch (Exception e) {
		      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
		      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		    }
		
	}
	*/
}
