package com.boosters.mda.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boosters.mda.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, String> {
	
	// 사용자ID을 통해 조회
	@Query("select u from UserEntity u where u.userId = ?1")
	UserEntity findByUserId(String userId);
	
	// 사용자ID와 패스워드를 통해 조회(로그인용)
	@Query("select u from UserEntity u where u.userId = ?1 and u.password = ?2")
	UserEntity findByUserIdAndPassword(String userId, String password);
	
}
