package fish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages =  {"fish.common", "fish.admin"})
@EnableJpaRepositories(basePackages = {"fish.common", "fish.admin"})
public class FishApplication {
	public static void main(String[] args) {
		SpringApplication.run(FishApplication.class, args);
	}
}