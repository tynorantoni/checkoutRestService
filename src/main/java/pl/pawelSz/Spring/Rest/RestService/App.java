package pl.pawelSz.Spring.Rest.RestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
 
@SpringBootApplication(scanBasePackages={"pl.pawelSz.Spring.Rest"})
public class App {
 
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
