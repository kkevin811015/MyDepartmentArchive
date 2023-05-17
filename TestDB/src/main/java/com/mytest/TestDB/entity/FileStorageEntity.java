package com.mytest.TestDB.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Table(name = "file")
@Entity
@Getter //getter setter은 없는거 추가한거임.
@Setter
public class FileStorageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="file_id")
	private Long id;
	private String userId;
	private String orgNm;
	private String saveNm;
	private String savedPath;
	
	@Builder
	public FileStorageEntity(Long id, String orgNm, String savedNm, String savedPath) {
		this.id = id;
		this.orgNm = orgNm;
		this.saveNm = savedNm;
		this.savedPath = savedPath;
	}
	
}
