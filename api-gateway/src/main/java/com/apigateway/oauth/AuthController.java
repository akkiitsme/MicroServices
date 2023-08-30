package com.apigateway.oauth;

import com.nimbusds.oauth2.sdk.AuthorizationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user ,Model model
            ){
        logger.info("Email ID: "+user.getEmail());

        AuthResponse auth  = new AuthResponse();
        //Setting email to authResponse
        auth.setUserId(user.getEmail());

        //Setting Token to auth response
        auth.setAccessToken(client.getAccessToken().getTokenValue());
        auth.setRefreshToken(Objects.requireNonNull(client.getRefreshToken()).getTokenValue());
        auth.setExpireAt(Objects.requireNonNull(client.getAccessToken().getExpiresAt()).getEpochSecond());

        //Getting User Role
        List<String> authorities = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        //Setting User Role
        auth.setAuthorities(authorities);
        return new ResponseEntity<>(auth, HttpStatus.OK);
    }
}
