package com.boosters.mda.model;

import com.boosters.mda.entity.FileStorageEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileStorageModel {
	private String id;
	private String userId;
	private String fName;
	private String fUri;
	private String time;
	
	public FileStorageModel(final FileStorageEntity entity) {
		this.id = entity.getId();
		this.userId = entity.getUserId();
		this.fName = entity.getFName();
		this.fUri = entity.getFUri();
		this.time = entity.getTime();
	}
	
	public static FileStorageEntity toEntity(final FileStorageModel model) {
		return FileStorageEntity.builder()
				.id(model.getId())
				.userId(model.getUserId())
				.fName(model.getFName())
				.fUri(model.getFUri())
				.time(model.getTime())
				.build();
	}
}
