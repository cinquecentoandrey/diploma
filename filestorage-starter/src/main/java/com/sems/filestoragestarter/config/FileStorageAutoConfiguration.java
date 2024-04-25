package com.sems.filestoragestarter.config;

import com.sems.filestoragestarter.client.FileStorageFeignClient;
import com.sems.filestoragestarter.service.FileStorageService;
import com.sems.filestoragestarter.service.impl.FileStorageServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableFeignClients(basePackages = {"com.sems.filestoragestarter"})
public class FileStorageAutoConfiguration {

    private final FileStorageFeignClient client;

    @Bean
    public FileStorageService fileStorageService() {
        log.debug("Create FileStorageServiceImpl bean");
        return new FileStorageServiceImpl(client);
    }

}
