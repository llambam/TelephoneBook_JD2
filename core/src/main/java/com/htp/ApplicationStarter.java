package com.htp;

import com.htp.config.core.DatabaseConfig;
import com.htp.config.core.JdbcTemplateConfig;
import com.htp.config.swagger.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAspectJAutoProxy
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(
    scanBasePackages = {"com.htp"},
    exclude = JacksonAutoConfiguration.class)
@Import({DatabaseConfig.class, JdbcTemplateConfig.class, SwaggerConfig.class})
public class ApplicationStarter extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationStarter.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ApplicationStarter.class);
  }
}
