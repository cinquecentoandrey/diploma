package com.sems.filestorageservice.controller;

import com.sems.filestorageservice.service.FsFileService;
import com.sems.filestoragestarter.dto.FsFileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Executable;

@RestController
@RequestMapping("/api/storage")
@RequiredArgsConstructor
public class FsFileController {

    private final FsFileService fsFileService;

    @GetMapping(value = "/file-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FsFileDto> getFileInfo(@RequestParam(value = "uuid") String uuid) {
        return ResponseEntity.ok(fsFileService.getInfoByGuid(uuid));
    }

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FsFileDto> upload(@RequestPart("file") MultipartFile file,
                                     @RequestParam(value = "zip", required = false) boolean zip) {
        try {
            return ResponseEntity.ok(fsFileService.saveFile(file, zip));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    @GetMapping(value = "/storage/download")
    ResponseEntity<byte[]> downloadByte(@RequestParam(value = "uuid") String uuid) {
        FsFileDto dto = fsFileService.getInfoByGuid(uuid);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dto.getOriginalName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(dto.getSize())
                .body(fsFileService.downloadBytes(uuid));
    }

    // todo authority
    @DeleteMapping("/cleanup")
    public boolean cleanup() {
        return fsFileService.cleanup();
    }

}
