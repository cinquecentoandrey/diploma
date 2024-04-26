package com.sems.filestorageservice.service;

import com.sems.filestoragestarter.dto.FsFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FsFileService {

    FsFileDto saveFile(MultipartFile file, boolean zip);

    FsFileDto saveFile(File file, boolean zip);

    FsFileDto getInfoByGuid(String guid);

    byte[] downloadBytes(String guid);
    boolean cleanup();
}
