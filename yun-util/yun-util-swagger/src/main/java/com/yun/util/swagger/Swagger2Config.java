package com.yun.util.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yun
 * created_time 2019-02-25 13:12.
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Autowired
    private SwaggerPara swaggerPara;

    @Autowired
    private SwaggerProperty prop;

    @Bean
    public Docket createRestApi() {
        // 正式环境不显示接口文档-通过包名控制
        if (!prop.isEnableOnPro() && swaggerPara.isProEvn()) {
            return null;
        }

        List<Parameter> operationParameters = new ArrayList<Parameter>();

        boolean hasTokenPara = false;
        boolean hasDevicePara = false;

        if (prop.getPara() != null && swaggerPara != null) {
            for (SwaggerProperty.Para para : prop.getPara()) {
                if (para.getName().equals(swaggerPara.getTokenAuthKey())) {
                    hasTokenPara = true;
                } else if (para.getName().equals(swaggerPara.getDeviceTypeKey())) {
                    hasDevicePara = true;
                }

                ParameterBuilder paraBd = new ParameterBuilder();
                paraBd.name(para.getName())
                        .description(para.getDescription())
                        .modelRef(new ModelRef(para.getModelRef()))
                        .parameterType(para.getParamType())
                        .required(para.isRequired())
                        .build();
                operationParameters.add(paraBd.build());
            }
        }

        // 没有 token 则自动添加
        if (!hasTokenPara) {
            ParameterBuilder tokenBd = new ParameterBuilder();
            tokenBd.name(swaggerPara.getTokenAuthKey())
                    .description("登录后的token，有则填")
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .required(false)
                    .build();
            operationParameters.add(tokenBd.build());
        }

        if (!hasDevicePara) {
            ParameterBuilder tokenBd = new ParameterBuilder();
            tokenBd.name(swaggerPara.getDeviceTypeKey())
                    .description("设备类型")
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .required(false)
                    .build();
            operationParameters.add(tokenBd.build());
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(operationParameters)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(prop.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(prop.getTitle())
                .description(prop.getDescription())
                .version(prop.getVersion())
                .build();
    }
}
