package com.galka.springbootai.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
@ComponentScan(basePackages = {"com.galka.springbootai.api"})
public class OpenApiConfig {

static {
    SpringDocUtils.getConfig().addRestControllers(Controller.class);

}
    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI().info(new Info()
                        .title("SpringBootAi API")
                        .description("SpringBootAi API Documentation"))
                .components(new Components().addPathItem("", new PathItem()));
    }
}
