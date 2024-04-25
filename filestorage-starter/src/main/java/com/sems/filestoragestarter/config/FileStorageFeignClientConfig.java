package com.sems.filestoragestarter.config;

import feign.Retryer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FileStorageFeignClientConfig {

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(3000, 6000, 3);
    }

}
