package chengweiou.universe.wormhole;

import chengweiou.universe.wormhole.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class WormholeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WormholeApplication.class, args);
	}
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/andromeda/**").uri("http://andromeda:8906/andromeda"))
				.route(r -> r.path("/contentcensor/**").uri("http://contentcensor-heart:8906/contentcensor").filter(new AuthFilter()))
				.build();
	}
}
