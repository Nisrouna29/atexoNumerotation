package com.test.technique.numerotation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration class for customizing web-related settings.
 *
 * This class implements `WebMvcConfigurer` and is used to customize the default Spring MVC
 * configuration. Specifically, it configures Cross-Origin Resource Sharing (CORS) settings
 * to allow requests from specific origins, HTTP methods, and headers.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS settings for the application.
     *
     * This method allows cross-origin requests from a specific domain (in this case,
     * `http://localhost:4200`) and defines which HTTP methods and headers are allowed
     * for these requests. It also specifies that credentials (cookies, HTTP authentication, etc.)
     * are not allowed to be sent with the requests.
     *
     * @param registry The CORS registry used to configure the CORS mappings.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("http://localhost:4200") // URL of the allowed origin (e.g., Angular dev server)
                .allowedOrigins("*") // Allow any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allow any headers
                .allowCredentials(false); // Do not allow credentials (cookies, HTTP authentication, etc.)
    }
}
