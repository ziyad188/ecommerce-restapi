package ziyad.com.ecommercerestapi;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
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
