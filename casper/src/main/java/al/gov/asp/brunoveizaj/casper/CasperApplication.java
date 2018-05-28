package al.gov.asp.brunoveizaj.casper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CasperApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasperApplication.class, args);
	}
}
