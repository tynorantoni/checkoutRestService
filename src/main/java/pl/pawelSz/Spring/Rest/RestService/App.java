package pl.pawelSz.Spring.Rest.RestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
/**
*
* @author Paweł Szymaszek
* @version 1.0
* @since 21.09.2017
*
*/ 

@SpringBootApplication(scanBasePackages={"pl.pawelSz.Spring.Rest"})
public class App {
 
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
