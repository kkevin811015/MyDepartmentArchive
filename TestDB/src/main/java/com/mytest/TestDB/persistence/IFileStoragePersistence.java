package com.mytest.TestDB.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mytest.TestDB.entity.FileStorageEntity;

@Repository
public interface IFileStoragePersistence extends JpaRepository<FileStorageEntity, Long> {
	@Query("select s from FileStorageEntity s where s.userId = ?1")
	List<FileStorageEntity> findByUserId(String userId);
}
