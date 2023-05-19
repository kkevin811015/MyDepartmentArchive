package com.test1.demo;

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
	    private String orgNm;   
	    private String savedNm;
	    private String savedPath;
	    private String savedTime;
    
    public FileStorageDTO(final FileEntity entity){
    	 this.id = entity.getId();
         this.orgNm = entity.getOrgNm();
         this.savedNm = entity.getSavedNm();
         this.savedPath = entity.getSavedPath();
         this.savedTime = entity.getSavedTime();
    }
	public static FileEntity toEntity(final FileStorageDTO dto) {
		return FileEntity.builder()
				.id(dto.getId())
				.orgNm(dto.getOrgNm())
				.savedNm(dto.getSavedNm())
				.savedPath(dto.getSavedPath())
				.savedTime(dto.getSavedTime())
				.build();
	}


}
