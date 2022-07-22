package pl.fis.restapisummary.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.fis.restapisummary.filter.DecimalFilter;
import pl.fis.restapisummary.filter.TimeStampFilter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<DecimalFilter> decimalFilterRegistrationBean() {
        FilterRegistrationBean<DecimalFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new DecimalFilter());
        bean.addUrlPatterns("/api/decimal/*");
        bean.setName("DecimalFilter");
        bean.setOrder(2);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<TimeStampFilter> timeStampFilterRegistrationBean() {
        FilterRegistrationBean<TimeStampFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new TimeStampFilter());
        bean.addUrlPatterns("/api/number/*");
        bean.setName("TimeStampFilter");
        bean.setOrder(1);
        return bean;
    }
}
