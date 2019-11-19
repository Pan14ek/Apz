package ua.nure.apz.makieiev.apz.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@EnableEurekaServer
@EnableJpaRepositories(basePackages = {"ua.nure.apz.makieiev.apz.repository"})
@EntityScan("ua.nure.apz.makieiev.apz.model")
@EnableTransactionManagement
@SpringBootApplication
public class GamificationApplicationServer {

    public static void main(String[] args) {
        SpringApplication.run(GamificationApplicationServer.class, args);
    }

}