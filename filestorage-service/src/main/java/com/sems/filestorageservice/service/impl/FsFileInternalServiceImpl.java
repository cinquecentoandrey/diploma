package com.sems.filestorageservice.service.impl;

import com.sems.filestorageservice.repository.FsFileRepository;
import com.sems.filestorageservice.service.FsFileInternalService;
import com.sems.filestoragestarter.enums.FileDeleteType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class FsFileInternalServiceImpl implements FsFileInternalService {

    @Value(value = "${app.file-outdate-year}")
    private Long FILE_OUTDATED_YEAR;

    private final FsFileRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processOutDatedFiles() {
        LocalDateTime expDate = LocalDateTime.now().minusYears(FILE_OUTDATED_YEAR);

        repository.findAllByCreatedBefore(expDate).forEach(fsFile -> {
            File file = new File(fsFile.getPath());
            if (file.exists()) {
                if (file.delete()) {
                    fsFile.setIsDeleted(true);
                    fsFile.setDeletedDate(LocalDateTime.now());
                    fsFile.setDeleteType(FileDeleteType.AUTOMATICALLY);
                    fsFile.setDeletedBy("SYSTEM");
                    repository.save(fsFile);
                    log.debug("File with guid: {}, was deleted", fsFile.getGuid());
                } else {
                    log.debug("File with guid: {}, wasn't deleted", fsFile.getGuid());
                }
            } else {
                log.debug("File with guid: {}, wasn't found", fsFile.getGuid());
            }
        });
    }

}
