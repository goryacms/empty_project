package ru.bellintegrator.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import ru.bellintegrator.practice.guides.dao.DocDAO;
import ru.bellintegrator.practice.guides.dao.impl.CitizenshipDAOImpl;
import ru.bellintegrator.practice.guides.dao.impl.DocDAOImpl;
import ru.bellintegrator.practice.office.controller.OfficeController;
import ru.bellintegrator.practice.office.dao.impl.OfficeDAOImpl;
import ru.bellintegrator.practice.office.service.impl.OfficeServiceImpl;
import ru.bellintegrator.practice.organization.controller.OrganizationController;

import ru.bellintegrator.practice.organization.dao.impl.OrganizationDAOImpl;
import ru.bellintegrator.practice.organization.service.impl.OrganizationServiceImpl;
import ru.bellintegrator.practice.users.controller.UserController;
import ru.bellintegrator.practice.users.dao.impl.UserDAOImpl;
import ru.bellintegrator.practice.users.service.impl.UserServiceImpl;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@ImportResource("spring_mvc_config.xml")
@SpringBootApplication
@ComponentScan(basePackageClasses = {OrganizationController.class, OrganizationServiceImpl.class, OrganizationDAOImpl.class,
                                     OfficeController.class,       OfficeServiceImpl.class,       OfficeDAOImpl.class,
                                     UserController.class,         UserServiceImpl.class,         UserDAOImpl.class,
                                     DocDAOImpl.class,             CitizenshipDAOImpl.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    @Bean
    public TaskExecutor controllerPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() + 1);
        executor.setQueueCapacity(25);
        return executor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    @Bean
    public Docket postApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.bellintegrator.practice.organization"))
                .paths(regex("/api.*"))
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Sample with Swagger")
                .description("Spring REST Sample with Swagger")
                .contact("https://github.com/goryacms/empty_project")
                .version("1.0")
                .build();
    }
}