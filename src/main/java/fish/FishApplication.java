package fish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages =  {"fish.domain"})
@EnableJpaRepositories(basePackages = {"fish.domain"})
public class FishApplication {
	public static void main(String[] args) {
		SpringApplication.run(FishApplication.class, args);
	}
}