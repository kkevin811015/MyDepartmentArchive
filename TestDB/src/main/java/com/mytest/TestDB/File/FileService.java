package com.mytest.TestDB.File;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileService {
	@Value("${file.dir}") // beans에서 가져온 어노테이션
	private String fileDir;
	
	private final FileRepository fileRepository;
	
	public Long saveFile(MultipartFile files)throws IOException {
		if (files.isEmpty()) {
			return null;
		}
		// 원본 파일명 추출
		String origName = files.getOriginalFilename();
		
		//파일 이름으로 쓸 uuid(Universally Unique IDentifier)생성
		String uuid = UUID.randomUUID().toString();
		
		// 확장자 추출(ex : .png 등...)
		String extension = origName.substring(origName.lastIndexOf("."));
		
		//uuid와 확장자 결합
		String savedName = uuid + extension;
		
		//파일 불러올 시 사용할 경로
		String savedPath = fileDir + savedName;
		
		//파일 엔티티 생성
		FileEntity file = FileEntity.builder()
				.orgNm(origName)
				.savedNm(savedName)
				.savedPath(savedPath)
				.build()
				;
		
		// 로컬에 uuid를 파일명으로 저장
		files.transferTo(new File(savedPath));
		
		// 데이터베이스에 파일 정보 저장
		FileEntity savedFile = fileRepository.save(file);
		return savedFile.getId();
	}
}
