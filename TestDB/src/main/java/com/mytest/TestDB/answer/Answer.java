package com.mytest.TestDB.answer;

import java.time.LocalDateTime;

import com.mytest.TestDB.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Lob
    @Column(name = "file_data")
    private byte[] fileData;
	
	private LocalDateTime createDate;
	
	@ManyToOne
    private Question question;
}
