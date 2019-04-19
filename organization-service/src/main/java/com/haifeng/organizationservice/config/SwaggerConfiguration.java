package com.haifeng.organizationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;

import java.util.*;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.haifeng.organizationservice.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Department API").description("Documentation Department API v1.0").build());
    }

    private OAuth securitySchema() {

        List<AuthorizationScope> authorizationScopeList = new ArrayList();
        authorizationScopeList.add(new AuthorizationScope("select", "all"));
//        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
//        authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
//        authorizationScopeList.add(new AuthorizationScope("write", "access all"));

        List<GrantType> grantTypes = new ArrayList();
        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(authLink);

        grantTypes.add(creGrant);
        GrantType anotherGrant = new ClientCredentialsGrant(authLink);
//        GrantType anotherGrant = new MyClientCredentialGrant(authLink);

        grantTypes.add(anotherGrant);

        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);

    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/**"))
                .build();
    }
    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = new AuthorizationScope("select", "all");

        return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
    }
    private String clientId;
    private String clientSecret;
    @Value("${swagger.token-uri}")
    private String authLink;

    @Bean
    public SecurityConfiguration securityInfo() {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("grant_type","password");
        return new SecurityConfiguration(clientId, clientSecret, "", "","",queryParam,true);
    }
}
