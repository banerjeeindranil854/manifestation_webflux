package com.mtm.manifestation.config;

import com.mtm.manifestation.v1.api.AvatarApi;
import com.mtm.manifestation.v2.controller.V2WebfluxController;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public GroupedOpenApi apiV2() {
        return GroupedOpenApi.builder()
                .group("v2")
                .packagesToScan(V2WebfluxController.class.getPackageName())
                .build();
    }
    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan(AvatarApi.class.getPackageName())
                .build();
    }
}
