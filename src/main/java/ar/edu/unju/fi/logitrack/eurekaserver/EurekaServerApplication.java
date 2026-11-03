package ar.edu.unju.fi.logitrack.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Servidor de descubrimiento de LogiTrack (S3-01).
 * <p>
 * Los microservicios se registran acá al arrancar y se encuentran entre sí por su nombre lógico
 * (por ejemplo {@code COURIER-SERVICE}) sin conocer IP ni puerto. El puerto y las propiedades de Eureka
 * no están en el código: las entrega el config-server desde {@code eureka-server.yml}.
 * </p>
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
