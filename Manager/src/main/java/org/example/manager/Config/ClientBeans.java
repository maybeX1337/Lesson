package org.example.manager.Config;

import org.example.manager.Client.UsersRestClient;
import org.example.manager.Client.UsersRestClientRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {
    @Bean
    public UsersRestClient productsRestClient(
            @Value("${users.service.list.uri:http://localhost:8081}") String catalogueBaseUri,
        @Value("${users.service.username:}") String username,
        @Value("${users.service.password:}") String password){
        return new UsersRestClientRestClient(
                RestClient.builder().baseUrl(catalogueBaseUri)
                        .requestInterceptor(new BasicAuthenticationInterceptor(username, password))
                        .build()
        );
    }
}
