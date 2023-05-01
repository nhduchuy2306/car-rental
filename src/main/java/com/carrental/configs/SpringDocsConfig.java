package com.carrental.configs;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.carrental.utils.ReadJsonFileToJsonObject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;

@Configuration
@OpenAPIDefinition
public class SpringDocsConfig {
    
    @Bean
    public OpenAPI baseOpenAPI() throws IOException{

        ReadJsonFileToJsonObject readJsonFileToJsonObject = new ReadJsonFileToJsonObject();

        ApiResponse successResponse = new ApiResponse().content(
            new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                new io.swagger.v3.oas.models.media.MediaType().addExamples("default", 
                    new Example().value(readJsonFileToJsonObject.read().get("successResponse").toString()))
            )
        );

        ApiResponse badRequestResponse = new ApiResponse().content(
            new Content().addMediaType(
                MediaType.APPLICATION_JSON_VALUE, 
                new io.swagger.v3.oas.models.media.MediaType().addExamples("default", 
                    new Example().value(readJsonFileToJsonObject.read().get("badRequestResponse").toString()))
            )
        );

        ApiResponse internalServerResponse = new ApiResponse().content(
            new Content().addMediaType(
                MediaType.APPLICATION_JSON_VALUE, 
                new io.swagger.v3.oas.models.media.MediaType().addExamples("default", 
                    new Example().value(readJsonFileToJsonObject.read().get("internalServerResponse").toString()))
            )
        );

        Components components = new Components()
            .addResponses("Success", successResponse)
            .addResponses("BadRequest", badRequestResponse)
            .addResponses("InternalServerError", internalServerResponse);

        return new OpenAPI()
            .components(components)
            .info(
                new Info()
                    .title("Spring Docs")
                    .description("Spring Docs for Car Rental App")
                    .version("1.0.0")
            );
    }
}
