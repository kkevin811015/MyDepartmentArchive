package com.mytest.TestDB.File;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UploadController {
	private final FileService fileService;
	
	
	//upload code
	@GetMapping("/upload")
	public String testUploadForm() {
		return "file_upload_form";
	}
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("files") List<MultipartFile> files) throws IOException {
		fileService.saveFile(file);
		
		for(MultipartFile multipartFile : files) {
			fileService.saveFile(multipartFile);
		}
		return "redirect:/";
	}
	
	//download code
	
}
