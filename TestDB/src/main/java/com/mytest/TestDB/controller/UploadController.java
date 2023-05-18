package com.mytest.TestDB.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mytest.TestDB.service.FileStorageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/storage/{user}/upload")
public class UploadController {
//	@autowired? contructor and setter...? whyyyy so annotation requiredargsconstructor.
	@Autowired
	private final FileStorageService fileService;
	ModelAndView mnv;
	
//	//upload code
	@GetMapping
	public ModelAndView testUploadForm(@PathVariable("user") String userId) {
		mnv = new ModelAndView();
		mnv.setViewName("uploader");
		return mnv;
	}
	
	@PostMapping
	public ModelAndView uploadFile(@PathVariable("user") String userId,
			@RequestParam("files") List<MultipartFile> files)
					throws IOException {
		fileService.storeFiles(userId, files);
		mnv.setViewName("redirect:/storage/"+userId+"/download");
		return mnv;
	}
	
	//download code
	
}
