package org.ajou.realcoding.lolcrawler.lolcrawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RiotConfig {
    @Bean
    public RestTemplate createRiotTemplate() {
        return new RestTemplate();
    }
}
