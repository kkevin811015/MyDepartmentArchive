package com.mytest.TestDB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mytest.TestDB.dto.ResponseDTO;
import com.mytest.TestDB.entity.FileStorageEntity;
import com.mytest.TestDB.service.FileStorageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/storage/{user}")
public class DownloadController {
//	private final FileService fileService
	@Autowired
	private final FileStorageService fileService;
	ModelAndView mnv;
	
//	temptemptemp...
	
	@GetMapping("/download")
	public ModelAndView view(@PathVariable("user") String user) {
		mnv = new ModelAndView();
		mnv.setViewName("view");
//		List<FileStorageModel>models = fStorageService.observeFiles(user);
//		List<FileStorageDTO> dtos = models.stream()
//											.map(FileStorageDTO::new)
//											.collect(Collectors.toList());
//		
//		ResponseDTO<FileStorageDTO> filesInfo = ResponseDTO.<FileStorageDTO>builder()
//															.data(dtos)
//															.build();
//		
//		// Response Set to ModelAndView
//		mnv.addObject("files", filesInfo); //Files{ FileInfo{}, ..., }
//		
//		return mnv; // Return Set
		List<FileStorageEntity> entities = fileService.observeFiles(user);
		ResponseDTO<FileStorageEntity> filesInfo = ResponseDTO.<FileStorageEntity>builder()
				.data(entities)
				.build();
		
		mnv.addObject("files", filesInfo);
		return mnv;
	}
	
//	//이미지 출력
//	@GetMapping("/images/{fileId}")
//	@ResponseBody
//	public Resource downloadImage(@PathVariable("fileId") Long id, Model model) throws IOException {
//		FileEntity file = fileRepository.findById(id).orElse(null);
//		return new UrlResource("file:" + file.getSavedPath());
//		
//	} //UrlResource로 파일 읽고 @responsebody로 바이너리 변환
//	// urlresource는 resource잉ㄴ터페이스 구현체로, new urlresource("file:" + "파일저장 경로")로 사용하면 된다.
//	
//	//첨부 파일 다운로드 엔티티
//	@GetMapping("/attach/{id}")
//	public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {
//		FileEntity file = fileRepository.findById(id).orElse(null);
//		
//		UrlResource resource = new UrlResource("file:" + file.getSavedPath());
//		
//		String encodedFileName = UriUtils.encode(file.getOrgNm(), StandardCharsets.UTF_8);
//		
//		//파일 다운로드 대화상자가 드도록 하는 헤더 설정
//		// content-disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
//		String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";
//		
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
//	}
}
