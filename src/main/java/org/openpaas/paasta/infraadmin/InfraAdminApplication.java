package org.openpaas.paasta.infraadmin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

/**
 * The type Application.
 */

@EnableAutoConfiguration
@Configuration
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class InfraAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfraAdminApplication.class, args);
    }

}

