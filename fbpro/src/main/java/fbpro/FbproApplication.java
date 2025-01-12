package fbpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "fbpro") //(exclude={DataSourceAutoConfiguration.class})
public class FbproApplication {

	public static void main(String[] args) {
		SpringApplication.run(FbproApplication.class, args);
	}

}
