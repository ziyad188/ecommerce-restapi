package ziyad.com.ecommercerestapi;

import com.stripe.Stripe;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import jakarta.annotation.PostConstruct;
import lombok.Value;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog App Rest APIs",
				description = "Spring Boot Blog App Rest APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Mohammed Ziyad",
						email = "mohdziyadk@gmail",
						url = "https://webwic.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://webwic.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog App Documentation",
				url = "https://github.com/ziyad188/blog-webserver.git"
		)
)
public class ECommerceRestapiApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Configuration
	@ConfigurationProperties(prefix = "stripe")
	public class StripeConfig {

		private String apikey;

		public void setApikey(String apikey) {
			this.apikey = apikey;
			Stripe.apiKey = apikey;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ECommerceRestapiApplication.class, args);
	}

}
