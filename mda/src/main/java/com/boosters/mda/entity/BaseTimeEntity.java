package com.boosters.mda.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {// JpaAudit으로 시간기록 자동화
	// createdDate: DB기준 작성일
	@CreatedDate
	public LocalDateTime createdDate;
	
    // modifiedDate: DB기준 수정일
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}
/*
 * @MappedSuperclass: 이 어노테이션이 붙은 클래스를 Entity가 상속하면
 * 	해당 컬럼 값들을 상속받아 DB매핑 컬럼에 추가한다.
 * 해당 클래스는 DB테이블에 반영되지 않고 상속받은 entity에 구성된 컬럼들만 물려준다.
 * 
 * 주의해야 할 점은 @MappedSuperclass는 
 * Entity와 다르게 테이블과 매핑하는 어노테이션이 아니기 때문에 
 * DB 테이블에 반영되지 않는다. 따라서, 일반적으로 단독으로 사용할 일이 없으므로 
 * 위와 같이 추상 클래스(abstract)로 선언하는 것이 좋다.
 */