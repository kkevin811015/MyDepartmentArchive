package com.boosters.mda.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
	 * Request: userId
	 * Response: DTO: Files{ File{ fname, uri, userid, time }, ...iteration }
	 * Return: ModelAndView("storage[.html]")
	 * Process: ...service.StorageManagement.observeFile(userId)
	 */
	// Method Set
	@GetMapping
	public ModelAndView FileListDisplayController(@PathVariable("user") String userId) {
		// View Set:
		mnv = new ModelAndView("storage");
		
		// Process Set:
		// Response Load From Service
		// 변경 예정
		List<FileStorageDTO> dtos = fStorageService.observeFiles(userId);
		
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
	 * Request: Form: MultipartFile, userId
	 * Response: None
	 * Return: Message: "File Uploaded"
	 * Process: ...service.StorageManagement.FilesStore(MultipartFile, userId)
	 */
	@PostMapping("/upload")
	public ModelAndView FileUploadController(
			@PathVariable("user") String userId,
			@RequestParam("files") List<MultipartFile> files
			) {
		
		boolean filesave = fStorageService.storeFiles(userId, files);
		
		if (filesave) { // Succeed
			
			mnv.setViewName("redirect:/storage/"+userId+"/");
		}else { // Failed
			
			mnv.setViewName("redirect:/");
		}
		return mnv;
	}
	
//	/*
//	 * Function: File Download Controller
//	 * Method: Get
//	 * Request: file Uri
//	 * Response: files from specified path
//	 * Return: files
//	 * 			Message: "File Uploaded"
//	 * Process: ...
//	 */
//	@GetMapping("/download/{fileName:.+}")
//	public ResponseEntity<Resource> exampleFileDownloadController(
//			@PathVariable("user") String userId,
//			@PathVariable("fileName") String fileName) {
//		
//		Resource file = fStorageService.loadFile(userId, fileName);
//		
////		mnv.setViewName("redirect:/storage/"+userId+"/");
//		
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
//	}
//	
//	
//	/*
//	 * Function: File Download Controller
//	 * Method: Get
//	 * Request: file Uri
//	 * Response: files from specified path
//	 * Return: files
//	 * 			Message: "File Uploaded"
//	 * Process: ...
//	 */
//	@GetMapping("/download/{files}")
//	public ResponseEntity<List<Resource>> FileDownloadController(
//			@PathVariable("user") String userId,
//			@PathVariable("files") List<String> fileNames) {
//		
//		// 1) request validation
//		//		pass...
//		
//		// 2) call searching function from service layer
//		List<Resource> file = fStorageService.loadFiles(userId, fileNames);
//		
////		mnv.setViewName("redirect:/storage/"+userId+"/");
//		
//		return null;
//	}
	
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
