package com.sems.filestoragestarter.service.impl;

import com.sems.common.enums.ErrorMessage;
import com.sems.filestoragestarter.client.FileStorageFeignClient;
import com.sems.filestoragestarter.dto.FsFileDto;
import com.sems.filestoragestarter.exception.FileStorageRequestException;
import com.sems.filestoragestarter.service.FileStorageService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerErrorException;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final FileStorageFeignClient client;

    @Override
    public FsFileDto getMetaData(String uuid) {
        try {
            return client.getMetaData(uuid).getBody();
        } catch (ServerErrorException ex) {
            log.debug("Get file info error: {}", ex.getMessage());
            throw new FileStorageRequestException(ErrorMessage.FILE_UPLOADING_ERROR.getMessage());
        }
    }

    @Override
    public FsFileDto upload(File file, boolean zip) {
        try {
            return client.upload(file, zip).getBody();
        } catch (ServerErrorException ex) {
            log.debug("File upload error: {}", ex.getMessage());
            throw new FileStorageRequestException(ErrorMessage.FILE_UPLOADING_ERROR.getMessage());
        }
    }

    @Override
    public FsFileDto upload(MultipartFile file, boolean zip) {
        try {
            return client.upload(file, zip).getBody();
        } catch (ServerErrorException ex) {
            log.debug("File upload error: {}", ex.getMessage());
            throw new FileStorageRequestException(ErrorMessage.FILE_UPLOADING_ERROR.getMessage());
        }
    }

    @Override
    public Response download(String uuid) {
        try {
            return client.download(uuid);
        } catch (ServerErrorException ex) {
            log.debug("File download error: {}", ex.getMessage());
            throw new FileStorageRequestException(ErrorMessage.FILE_UPLOADING_ERROR.getMessage());
        }
    }

    @Override
    public byte[] downloadByte(String uuid) {
        try {
            return client.downloadByte(uuid).getBody();
        } catch (ServerErrorException ex) {
            log.debug("File download error: {}", ex.getMessage());
            throw new FileStorageRequestException(ErrorMessage.FILE_UPLOADING_ERROR.getMessage());
        }
    }

    @Override
    public Boolean cleanup() {
        try {
            return client.cleanup().getBody();
        } catch (ServerErrorException ex) {
            log.debug("File dstorage cleanup error: {}", ex.getMessage());
            throw new FileStorageRequestException(ErrorMessage.FILE_UPLOADING_ERROR.getMessage());
        }
    }

}
