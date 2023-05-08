package com.mytest.TestDB.File;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DownloadController {
//	private final FileService fileService;
	private final FileRepository fileRepository;
	
	@GetMapping("/view")
	public String view(Model model) {
		List<FileEntity> files = fileRepository.findAll();
		model.addAttribute("all",files);
		return "view";
	}
	
	//이미지 출력
	@GetMapping("/images/{fileId}")
	@ResponseBody
	public Resource downloadImage(@PathVariable("fileId") Long id, Model model) throws IOException {
		FileEntity file = fileRepository.findById(id).orElse(null);
		return new UrlResource("file:" + file.getSavedPath());
		
	} //UrlResource로 파일 읽고 @responsebody로 바이너리 변환
	// urlresource는 resource잉ㄴ터페이스 구현체로, new urlresource("file:" + "파일저장 경로")로 사용하면 된다.
	
	//첨부 파일 다운로드 엔티티
	@GetMapping("/attach/{id}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {
		FileEntity file = fileRepository.findById(id).orElse(null);
		
		UrlResource resource = new UrlResource("file:" + file.getSavedPath());
		
		String encodedFileName = UriUtils.encode(file.getOrgNm(), StandardCharsets.UTF_8);
		
		//파일 다운로드 대화상자가 드도록 하는 헤더 설정
		// content-disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
		String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
	}
}
