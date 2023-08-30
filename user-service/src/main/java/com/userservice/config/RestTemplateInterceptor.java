package com.userservice.config;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;
import java.util.Objects;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    private OAuth2AuthorizedClientManager clientManager;


    public RestTemplateInterceptor(OAuth2AuthorizedClientManager clientManager){
        this.clientManager = clientManager;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request , byte[] body , ClientHttpRequestExecution execution) throws IOException {
        String token = clientManager.authorize(OAuth2AuthorizeRequest.
                        withClientRegistrationId("my-internal-client")
                        .principal("internal").build())
                .getAccessToken().getTokenValue();

        System.out.println("Rest Interceptor Token: "+token);

        request.getHeaders().add("Authorization","Bearer "+token);
        return execution.execute(request, body);
    }
}