package cn.darkjrong.swagger.gateway;

import com.netflix.zuul.filters.ZuulServletFilter;
import com.netflix.zuul.http.ZuulServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * swagger网关自动配置
 *
 * @author Rong.Jia
 * @date 2021/07/09
 */
@ComponentScan
@Configuration
public class SwaggerGatewayAutoConfiguration {

    @Configuration
    @ConditionalOnClass({ZuulServlet.class, ZuulServletFilter.class})
    public class ZuulSwaggerConfiguration {

        @Bean
        public ZuulSwaggerProvider zuulSwaggerProvider(org.springframework.cloud.netflix.zuul.filters.RouteLocator  routeLocator,
                                                       ZuulProperties zuulProperties) {
            return  new ZuulSwaggerProvider(routeLocator,zuulProperties);
        }

    }

    @Configuration
    @ConditionalOnClass({WebFluxConfigurer.class})
    public class GatewaySwaggerConfiguration {

        @Bean
        public GatewaySwaggerProvider gatewaySwaggerProvider(org.springframework.cloud.gateway.route.RouteLocator routeLocator,
                                                             GatewayProperties gatewayProperties) {
            return new GatewaySwaggerProvider(routeLocator,gatewayProperties);
        }

        @Bean
        public SwaggerHeaderFilter swaggerHeaderFilter(){
            return new SwaggerHeaderFilter();
        }
    }





}
