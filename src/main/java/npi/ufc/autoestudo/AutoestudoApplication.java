package npi.ufc.autoestudo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AutoestudoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoestudoApplication.class, args);
	}

	@RequestMapping(value="/")
	public String hello(){
		return "Hello World";
	}
}
