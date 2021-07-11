package cn.darkjrong.swagger.gateway;

import springfox.documentation.swagger.web.SwaggerResource;

/**
 * Swagger 工具类
 *
 * @author Rong.Jia
 * @date 2021/07/09
 */
public class SwaggerUtils {

    private static final String SWAGGER_VERSION = "2.0";
    public static final String API_URI = "/v2/api-docs";

    /**
     * Swagger的资源
     *
     * @param name     的名字
     * @param location 位置
     * @return {@link SwaggerResource} Swagger资源
     */
    public static SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(SWAGGER_VERSION);
        return swaggerResource;
    }

}
