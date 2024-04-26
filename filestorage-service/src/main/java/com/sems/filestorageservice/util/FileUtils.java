package com.sems.filestorageservice.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

@Slf4j
public final class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException();
    }

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

    public static boolean zipFile(Path source, Path destination) {
        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(destination.toFile().toPath()));
             FileInputStream fis = new FileInputStream(source.toFile())) {
            zipOut.putNextEntry(new ZipEntry(source.toFile().getName()));
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            return true;
        } catch (Exception ex) {
            log.error("Error zipping file, message '{}'", ex.getMessage(), ex);
            return false;
        }
    }

}
