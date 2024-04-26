package com.sems.filestorageservice.repository;

import com.sems.filestorageservice.model.domain.FsFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FsFileRepository extends JpaRepository<FsFile, Long> {
    List<FsFile> findAllByCreatedBefore(LocalDateTime expDate);

    Optional<FsFile> findByGuid(String guid);
}
