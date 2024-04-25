package com.sems.filestoragestarter.client;

import com.sems.filestoragestarter.config.FileStorageFeignClientConfig;
import com.sems.filestoragestarter.dto.FsFileDto;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@FeignClient(name = "${apps.filestorage-service}", path = "/api",
        configuration = {FileStorageFeignClientConfig.class})
public interface FileStorageFeignClient {

    @GetMapping(value = "/storage/file-info")
    ResponseEntity<FsFileDto> getMetaData(@RequestParam("uuid") String uuid);

    @PostMapping(value = "/storage/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FsFileDto> upload(@RequestPart("file") File file,
                                     @RequestPart(value = "zip") boolean zip);

    @PostMapping(value = "/storage/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FsFileDto> upload(@RequestPart("file") MultipartFile file,
                                     @RequestPart(value = "zip") boolean zip);

    @GetMapping(value = "/storage/download")
    Response download(@RequestParam(value = "uuid") String uuid);

    @GetMapping(value = "/storage/download")
    ResponseEntity<byte[]> downloadByte(@RequestParam(value = "uuid") String uuid);

    @DeleteMapping(value = "/storage/clean-up")
    ResponseEntity<Boolean> cleanup();

}
