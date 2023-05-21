package com.boosters.mda.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.boosters.mda.entity.FileStorageEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileStorageDTO {
	
	private Long id;
	
	// Who
	private String userId;
	
	// When
	private LocalDateTime time;
	
	// Where
	private String fileUri;
	
	// What 1
	private String contentType;
	
	// What 2
	private String fileName;
	
	public FileStorageDTO(final FileStorageEntity entity) {
		this.id = entity.getId(); // 삭제 예정
		this.userId = entity.getUserId(); // Who
		this.contentType = entity.getContentType(); // What 1
		this.fileName = entity.getFileOrgName(); // What 2
		this.fileUri = entity.getSavedUri(); // Where
		this.time = entity.getModifiedDate(); // When
	}
	
	// toEntity() 사용 보류
	public static FileStorageEntity toEntity(final FileStorageDTO dto) {
		return FileStorageEntity.builder()
				//.id(dto.getId()) entity에서 자동 생성
				.userId(dto.getUserId()) // Who
				// When is automatically created by JpaAuditing
				.savedUri(dto.getFileUri()) // Where
				.fileOrgName(dto.getFileName()) // What 1
				.fileUUIDName(UUID.randomUUID().toString()+ "-" + dto.getFileName()) // What 2
				.extension(dto.getFileName().substring(dto.getFileName().lastIndexOf("."))) // What 3
				.contentType(dto.getContentType()) // What 4
				.build();
	}
}
