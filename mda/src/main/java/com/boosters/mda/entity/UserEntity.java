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
@Table(name="ServiceUser")
public class UserEntity {

	@Id
	private String userId;			// 사용자ID
	private String password;		// 패스워드
	private String familyName;		// 사용자 성씨
	private String firstName;		// 사용자 이름
	private String emailAddress;	// 이메일
	private String personalNumber;	// 주민번호
	private Integer rights;			// 사용자 권한(!리눅스 권한체계 참고!)
	
}
