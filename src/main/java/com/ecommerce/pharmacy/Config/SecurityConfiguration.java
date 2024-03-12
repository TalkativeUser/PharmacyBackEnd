//package com.ecommerce.pharmacy.Config;
//
//import com.okta.spring.boot.oauth.Okta;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.accept.ContentNegotiationStrategy;
//import org.springframework.web.accept.HeaderContentNegotiationStrategy;
//
//@Configuration
//public class SecurityConfiguration {
//    @Autowired
//    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService;
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.csrf().disable();
//
//        http.authorizeRequests(configurer ->
//                configurer.
//                        antMatchers("/api/category/secure/**",
//                                "/api/product/secure/**",
//                                "/api/review/secure/**",
//                                "/api/cart/secure/**",
//                                "/api/checkout/secure/**"
//                                )
//                        .authenticated())
//                .oauth2ResourceServer()
//                .jwt();
//
//        http.cors();
//
//        http.setSharedObject(ContentNegotiationStrategy.class,
//                new HeaderContentNegotiationStrategy());
//
//        Okta.configureResourceServer401ResponseBody(http);
//
//        return http.build();
//    }
//}
