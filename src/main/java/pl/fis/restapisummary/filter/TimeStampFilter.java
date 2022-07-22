package pl.fis.restapisummary.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class TimeStampFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if(httpServletRequest.getMethod().equals(HttpMethod.GET.toString())) {
            long startTime = System.nanoTime();
            filterChain.doFilter(servletRequest, servletResponse);
            long endTime = System.nanoTime();
            log.info(String.format("Execution of request took %d nanoseconds.", endTime - startTime));
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
