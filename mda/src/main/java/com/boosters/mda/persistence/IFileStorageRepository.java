package com.boosters.mda.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boosters.mda.entity.FileStorageEntity;

@Repository
public interface IFileStorageRepository extends JpaRepository<FileStorageEntity, String> {
	/*
	 * JpaRepository provide 'Query Method'.
	 * And, that pattern is 
	 * 		"find + By + VariableName [+ Keyword + VariableName ...](Variable parameter)
	 */
	
	/*
	 * Function: Find Entity By userid
	 * SameAs: Select * from storage t where t.userid = ?1;
	 */
	@Query("select s from FileStorageEntity s where s.userId = ?1")
	List<FileStorageEntity> findByUserId(String userId);
}
