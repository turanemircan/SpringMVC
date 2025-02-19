package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe")
@EnableWebMvc // MVC'yi etkinleştirir
public class WebMvcConfig implements WebMvcConfigurer {

    // view resolver: controllerdan sadece sayfanın ismi:students
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/"); //view dosyası nerede:/WEB-INF/views/students
        viewResolver.setSuffix(".jsp");//dosyanın uzantısı nedir: /WEB-INF/views/students.jsp
        viewResolver.setViewClass(JstlView.class);
        // JSTL: JavaStandartTagLibrary: JSP içinde daha az Java kodu yazmamızı sağlar
        return viewResolver;
    }

    // handlermapping: statik sayfa içeren isteklerin servleta gönderilmesine gerek yok

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**") // Bu url ile gelen istekleri statik olarak sun
                .addResourceLocations("/resources/") // Statik olan kaynakların yeri
                .setCachePeriod(0); // cache periyodu için süre verilebilir.
    }
}
