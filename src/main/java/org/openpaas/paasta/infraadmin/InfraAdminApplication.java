package org.openpaas.paasta.infraadmin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The type Application.
 */


@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class InfraAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfraAdminApplication.class, args);
    }
}

