package com.challenge.spo.workforcecalculator.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build();
    }

    private Predicate<String> paths() {
        return not(
                or(
                        regex("/internal.*"),
                        regex("/error.*"),
                        regex("/")
                )
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("SPO Coding Challenge")
                .title("Calculate Cleaning Crews")
                .version("0.1")
                .build();
    }

}