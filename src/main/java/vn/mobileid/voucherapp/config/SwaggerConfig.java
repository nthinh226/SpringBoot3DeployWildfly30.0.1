package vn.mobileid.voucherapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApiSpec() {
        // Define the ApiFieldError schema first
        ObjectSchema apiFieldErrorSchema = (ObjectSchema) new ObjectSchema()
                .addProperty("code", new StringSchema())
                .addProperty("message", new StringSchema())
                .addProperty("property", new StringSchema())
                .addProperty("rejectedValue", new ObjectSchema())
                .addProperty("path", new StringSchema());

        // Define the ApiErrorResponse schema with a reference to ApiFieldError
        ObjectSchema apiErrorResponseSchema = (ObjectSchema) new ObjectSchema()
                .addProperty("status", new IntegerSchema())
                .addProperty("code", new StringSchema())
                .addProperty("message", new StringSchema())
                .addProperty("fieldErrors", new ArraySchema().items(apiFieldErrorSchema)); // Reference ApiFieldError directly

        // Define components and add schemas
        Components components = new Components()
                .addSchemas("ApiFieldError", apiFieldErrorSchema) // Add ApiFieldError schema to components
                .addSchemas("ApiErrorResponse", apiErrorResponseSchema); // Add ApiErrorResponse schema to components

        // Create and return OpenAPI object
        return new OpenAPI().components(components);
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        // add error type to each operation
        return (operation, handlerMethod) -> {
            operation.getResponses().addApiResponse("4xx/5xx", new ApiResponse()
                    .description("Error")
                    .content(new Content().addMediaType("*/*", new MediaType().schema(
                            new Schema<MediaType>().$ref("ApiErrorResponse")))));
            return operation;
        };
    }

}
