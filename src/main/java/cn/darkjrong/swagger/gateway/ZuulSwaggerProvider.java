package cn.darkjrong.swagger.gateway;

import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 聚合其他微服务的api资源
 *
 * @author Rong.Jia
 * @date 2021/05/11 13:33
 */
public class ZuulSwaggerProvider implements SwaggerResourcesProvider {

    private RouteLocator routeLocator;
    private ZuulProperties zuulProperties;

    public ZuulSwaggerProvider(RouteLocator routeLocator,
                               ZuulProperties zuulProperties) {
        this.routeLocator = routeLocator;
        this.zuulProperties = zuulProperties;
    }

    @Override
    public List<SwaggerResource> get() {

        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        //取出gateway的route
        routeLocator.getRoutes().forEach(route -> routes.add(route.getId()));

        Map<String, ZuulProperties.ZuulRoute> zuulRouteMap = zuulProperties.getRoutes();
        zuulRouteMap.entrySet().stream()
                .filter(zuulRoute -> routes.contains(zuulRoute.getValue().getId()))
                .forEach(zuulRoute -> resources.add(SwaggerUtils.swaggerResource(zuulRoute.getKey(),
                        zuulRoute.getValue().getPath().replace("/**", SwaggerUtils.API_URI))));
        return resources;
    }













}