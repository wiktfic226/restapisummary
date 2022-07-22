package pl.fis.restapisummary.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecimalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if(httpServletRequest.getMethod().equals(HttpMethod.PUT.toString())) {
            List<String> allowedValues = new ArrayList<>(List.of("1", "2", "3", "4"));
            if(httpServletRequest.getHeader("decimalPlaces") != null && allowedValues.contains(httpServletRequest.getHeader("decimalPlaces")))
                filterChain.doFilter(servletRequest, servletResponse);
            else {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                Map<String, Object> errorDetails = new HashMap<>();
                ObjectMapper mapper = new ObjectMapper();
                errorDetails.put("errorMessage", "Value passed in decimal header is incorrect");
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                mapper.writeValue(httpServletResponse.getWriter(), errorDetails);
            }
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
