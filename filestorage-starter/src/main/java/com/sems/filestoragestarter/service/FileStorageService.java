package com.sems.filestoragestarter.service;

import com.sems.filestoragestarter.dto.FsFileDto;
import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileStorageService {

    FsFileDto getMetaData(String uuid);
    FsFileDto upload(File file, boolean zip);

    FsFileDto upload(MultipartFile file, boolean zip);

    Response download(String uuid);

    byte[] downloadByte(String uuid);

    Boolean cleanup();

}
