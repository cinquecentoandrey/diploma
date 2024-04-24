package com.sems.filestorageservice.repository;

import com.sems.filestorageservice.model.domain.FsFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FsFileRepository extends JpaRepository<FsFile, Long> {
}
