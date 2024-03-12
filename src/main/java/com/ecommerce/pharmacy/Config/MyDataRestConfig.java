package com.ecommerce.pharmacy.Config;

import com.ecommerce.pharmacy.Entity.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private String theAllowedOrigins = "http://localhost:4200";
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.DELETE,
                HttpMethod.PUT};

        config.exposeIdsFor(Product.class);
        config.exposeIdsFor(Review.class);
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Cart.class);
        config.exposeIdsFor(Address.class);
        config.exposeIdsFor(Customer.class);
        config.exposeIdsFor(Order.class);
        config.exposeIdsFor(OrderItem.class);

        disableHttpMethods(Address.class,config,theUnsupportedActions);
        disableHttpMethods(Customer.class,config,theUnsupportedActions);
        disableHttpMethods(Order.class,config,theUnsupportedActions);
        disableHttpMethods(OrderItem.class,config,theUnsupportedActions);
        disableHttpMethods(Cart.class,config,theUnsupportedActions);
        disableHttpMethods(Product.class, config, theUnsupportedActions);
        disableHttpMethods(Review.class, config, theUnsupportedActions);
        disableHttpMethods(Category.class,config,theUnsupportedActions);

        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethods(Class theClass,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions));
    }
}
