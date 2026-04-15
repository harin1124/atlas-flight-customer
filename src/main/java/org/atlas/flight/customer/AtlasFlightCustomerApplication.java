package org.atlas.flight.customer;

import org.atlas.flight.core.config.CryptoAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CryptoAutoConfiguration.class)
@SpringBootApplication
public class AtlasFlightCustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AtlasFlightCustomerApplication.class, args);
	}
}
