package com.haifeng.organizationservice.config;

import springfox.documentation.service.CredentialsGrant;

public class MyClientCredentialGrant extends CredentialsGrant {
    public MyClientCredentialGrant(String tokenUrl) {
        super("client_credentials", tokenUrl);
    }
}
