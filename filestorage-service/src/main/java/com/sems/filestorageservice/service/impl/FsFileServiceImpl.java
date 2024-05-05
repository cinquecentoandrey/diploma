package com.sems.filestorageservice.service.impl;

import com.sems.common.enums.ErrorMessage;
import com.sems.common.exception.EntityNotFoundException;
import com.sems.filestorageservice.exception.ByteDownloadException;
import com.sems.filestorageservice.exception.FileStorageException;
import com.sems.filestorageservice.mapper.FileMapper;
import com.sems.filestorageservice.model.domain.FsFile;
import com.sems.filestorageservice.repository.FsFileRepository;
import com.sems.filestorageservice.service.FsFileService;
import com.sems.filestorageservice.util.FileUtils;
import com.sems.filestoragestarter.dto.FsFileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FsFileServiceImpl implements FsFileService {

    @Value("${app.upload-directory}")
    private String UPLOAD_DIR;

    private final FsFileRepository fsFileRepository;
    private final FileMapper fileMapper;
    @Override
    @Transactional
    public FsFileDto saveFile(MultipartFile file, boolean zip) {
        String originalFileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        long fileSize = file.getSize();

        String guid = UUID.randomUUID().toString();
        String hash = null;
        try {
            hash = FileUtils.hashFile(file.getBytes());
            byte[] fileBytes = file.getBytes();
            Path filePath = Paths.get(UPLOAD_DIR, guid);
            Files.write(filePath, fileBytes);

            if (zip) {

            }
        } catch (IOException e) {
            throw new FileStorageException(ErrorMessage.FILE_SAVING_ERROR.getMessage());
        }

        FsFile fileEntity = FsFile.builder()
                .guid(guid)
                .originalName(originalFileName)
                .type(fileType)
                .size(fileSize)
                .path(UPLOAD_DIR + File.separator + guid)
                .hash(hash)
                .created(LocalDateTime.now())
                .isDeleted(false)
                .build();

        return fileMapper.domainToDto(fsFileRepository.save(fileEntity));
    }

    @Override
    public FsFileDto saveFile(File file, boolean zip) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public FsFileDto getInfoByGuid(String guid) {
        return fileMapper.domainToDto(getByGuid(guid));
    }

    @Override
    public byte[] downloadBytes(String guid) {
        try {
            return Files.readAllBytes(Paths.get(getByGuid(guid).getPath()));
        } catch (IOException e) {
            throw new ByteDownloadException(ErrorMessage.FILE_DOWNLOADING_ERROR.getMessage());
        }
    }

    @Override
    public boolean cleanup() {
        return false;
    }

    private FsFile getByGuid(String guid) {
        return fsFileRepository.findByGuid(guid)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND.getMessage()));
    }

}
