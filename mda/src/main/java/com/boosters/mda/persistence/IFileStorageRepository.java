package com.boosters.mda.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boosters.mda.entity.FileStorageEntity;

@Repository
public interface IFileStorageRepository extends JpaRepository<FileStorageEntity, Long> {
	/*
	 * JpaRepository provide 'Query Method'.
	 * And, that pattern is 
	 * 		"find + By + VariableName [+ Keyword + VariableName ...](Variable parameter)
	 */
	
	/*
	 * Function: Find Entity By userId
	 * SameAs: Select * from storage t where t.userId = ?1;
	 */
//	@Query("select s from FileStorageEntity s where s.userId = ?1")
	List<FileStorageEntity> findByUserId(String userId);
	
//	@Query("select s from FileStorageEntity s where s.userId = ?1 and s.fOrgName in ?2")
//	List<FileStorageEntity> findByUserIdAndFOrgNameIn(String userId, List<String> fOrgNames);
}
