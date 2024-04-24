package com.sems.filestorageservice.util;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

@NoArgsConstructor
public class FileUtils {

    @SneakyThrows
    public static String hashFile(byte[] file) {
        return md5Hex(file);
    }

    @SneakyThrows
    public static String hashFile(Path path) {
        try(InputStream is = Files.newInputStream(path)){
            return md5Hex(is);

        }
    }

}
