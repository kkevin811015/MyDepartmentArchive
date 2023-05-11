package com.boosters.mda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="User")
public class UserEntity {

	@Id
	private String userId;			// 사용자ID
	private String password;		// 패스워드
	private String name;			// 사용자 본명
	private String email;			// 이메일
	private String personalNumber;	// 주민번호
	private Integer rights;			// 사용자 권한(!리눅스 권한체계 참고!)
	
}
