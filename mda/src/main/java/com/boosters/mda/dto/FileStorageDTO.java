package com.boosters.mda.dto;

import com.boosters.mda.model.FileStorageModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileStorageDTO {
	private String id;
	private String userId;
	private String fName;
	private String fUri;
	private String time;
	
	public FileStorageDTO(final FileStorageModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.fName = model.getFName();
		this.fUri = model.getFUri();
		this.time = model.getTime();
	}
	
	public static FileStorageModel toModel(final FileStorageDTO dto) {
		return FileStorageModel.builder()
				.id(dto.getId())
				.userId(dto.getUserId())
				.fName(dto.getFName())
				.fUri(dto.getFUri())
				.time(dto.getTime())
				.build();
	}
}
