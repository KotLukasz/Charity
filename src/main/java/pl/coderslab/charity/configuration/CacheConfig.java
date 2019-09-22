package pl.coderslab.charity.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

	@Bean
	public CaffeineCache categories() {
		return new CaffeineCache("categories",
				Caffeine.newBuilder()
						.expireAfterAccess(1, TimeUnit.DAYS)
						.build());
	}

	@Bean
	public CaffeineCache institutions() {
		return new CaffeineCache("institutions",
				Caffeine.newBuilder()
						.expireAfterAccess(1, TimeUnit.DAYS)
						.build());
	}

}
