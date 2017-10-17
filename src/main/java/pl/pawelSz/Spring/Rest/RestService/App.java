package pl.pawelSz.Spring.Rest.RestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
/**
*
* @author Paweł Szymaszek
* @version 1.1
* @since 17.10.2017
*
*/ 

@SpringBootApplication(scanBasePackages={"pl.pawelSz.Spring.Rest"})
public class App {
 
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
