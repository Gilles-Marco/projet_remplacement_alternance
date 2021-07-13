package fr.miage.ter.comparcom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

@SpringBootApplication
public class ComparcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComparcomApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedOrigins("http://localhost:8081");
			}
		};
	}

	@Bean
	public SocketIOServer socketIoServer(){
		Configuration config = new Configuration();
		config.setHostname("localhost");
		config.setPort(8082);
		return new SocketIOServer(config);
	}
}
