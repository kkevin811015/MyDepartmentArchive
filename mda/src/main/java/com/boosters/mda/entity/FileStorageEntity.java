package com.boosters.mda.entity;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Storage")
public class FileStorageEntity extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userId;
	private String contentType;
	private String fOrgName;
	private String fUUIDName;
	private String extension;
	private String savedUri;
}
/* !! Backup !!
 * @GeneratedValue(generator="system-uuid")
 * GenericGenerator(name="system-uuid", strategy="uuid")
 */
/*
권장하는 식별자 구성 전략
(Long형) + (대체키) + (적절한 키 생성 전략) 사용

Long Type (아래 참고)
대체키 사용: 랜덤 값, 유휴 ID 등 비즈니스와 관계없는 값 사용
AUTO_INCREMENT 또는 Sequnce Object 사용
https://gmlwjd9405.github.io/2019/08/12/primary-key-mapping.html
*/