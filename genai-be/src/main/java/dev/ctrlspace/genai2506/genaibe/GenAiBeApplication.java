package dev.ctrlspace.genai2506.genaibe;

import dev.ctrlspace.genai2506.genaibe.controllers.UserController;
import dev.ctrlspace.genai2506.genaibe.models.entities.User;
import dev.ctrlspace.genai2506.genaibe.repositories.UserRepository;
import dev.ctrlspace.genai2506.genaibe.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
        UserController.class,
        GenAiBeApplication.class,
        UserService.class,
        UserRepository.class,
})
@EnableJpaRepositories(basePackageClasses = {UserRepository.class})
@EntityScan(basePackageClasses = {
        User.class
})
public class GenAiBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenAiBeApplication.class, args);
    }

}
