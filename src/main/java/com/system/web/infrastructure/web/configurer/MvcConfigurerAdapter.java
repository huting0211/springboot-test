package com.system.web.infrastructure.web.configurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.web.infrastructure.web.conversion.StringToEnumConverterFactory;
import com.system.web.infrastructure.web.menu.MenuInterceptor;
import com.system.web.infrastructure.web.security.UserObjectInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class MvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    // JavaConfig形式注册拦截器为一个Bean，类需要标记Configuration
    // 这样可以将拦截器的初始化交给SpringContext，拦截器里的Autowired才可以正确注入
    @Bean
    public HandlerInterceptor getUserObjectInterceptor(){
        return new UserObjectInterceptor();
    }

    @Bean
    public HandlerInterceptor getMenuInterceptor(){
        return new MenuInterceptor();
    }


    @Bean(name="conversionService")
    public ConversionServiceFactoryBean getConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<ConverterFactory> converters = new HashSet<>();
        converters.add(new StringToEnumConverterFactory());
        bean.setConverters(converters);
        return bean;
    }

    @Bean
    public ObjectMapper ObjectMapper(){
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserObjectInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/login", "/logout");

        registry.addInterceptor(getMenuInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/login", "/logout");

        super.addInterceptors(registry);
    }
}
