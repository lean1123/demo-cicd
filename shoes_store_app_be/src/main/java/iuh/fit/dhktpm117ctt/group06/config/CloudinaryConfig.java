package iuh.fit.dhktpm117ctt.group06.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dr7uxdi9o",
                "api_key", "143964785271212",
                "api_secret", "0LKmt35XdACWXKytqHIjqRGQaQo",
                "folder", "ShoesShopApp"
        ));
    }
}
