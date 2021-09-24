package com.dataw.rhino.config;

import com.dataw.rhino.App;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author programmer
 * @date 2019年8月25日 下午1:05:04
 */
@Configuration
@EnableSwagger2
//@com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI
public class SwaggerUIConfig {

    @Bean
    public Docket customDocket() {
        String basePackage = App.class.getPackage().getName();
        Predicate<RequestHandler> selector = Predicates.and(RequestHandlerSelectors.basePackage(basePackage));

        ApiInfo apiInfo = new ApiInfoBuilder()/* */
                .title("围术期数据同步工具")/* */
                .description("数据从数据库同步到elasticsearch")/* */
                .version(ApiInfo.DEFAULT.getVersion())/* */
                .termsOfServiceUrl("http://www.aidcloud.cn")/* */
                .license(ApiInfo.DEFAULT.getLicense())/* */
                .licenseUrl(ApiInfo.DEFAULT.getLicenseUrl())/* */
                .build();

//        String tagStr = Paths.splitCamelCase(Db2EsController.class.getSimpleName(), "-").toLowerCase();
//        Tag first = new Tag(tagStr, "数据库到Elasticsearch", 1);

        return new Docket(DocumentationType.SWAGGER_2)/* */
                .apiInfo(apiInfo)/* */
                .groupName("知汇科技")/* */
//                .tags(first)/* */
                .select()/* */
                /* */.apis(selector)/* */
                .build();
    }

}
