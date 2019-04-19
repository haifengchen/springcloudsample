package com.haifeng.departmentservice.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Arrays;

@Configuration
public class ClientConfiguration {

   /* @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;*/
//    @Value("${security.oauth2.client.scope}")
//    private String scope;

    @Bean
    RequestInterceptor oauth2FeignRequestInterceptor() {
//        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
                requestTemplate.header("Authorization", "Bearer " + details.getTokenValue());

            }
        };
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

   /* private OAuth2ProtectedResourceDetails resource() {


        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setGrantType("client_credentials");
        return resourceDetails;
    }*/
}
