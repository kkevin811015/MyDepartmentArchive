package com.test1.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Table(name="file")
@Entity
@Data
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    private Long id;
    private String orgNm;   
    private String savedNm;
    private String savedPath;
    private String savedTime;

    @Builder
    public FileEntity(Long id, String orgNm, String savedNm, String savedPath,String savedTime) {
        this.id = id;
        this.orgNm = orgNm;
        this.savedNm = savedNm;
        this.savedPath = savedPath;
        this.savedTime = savedTime;
    }
    


}
