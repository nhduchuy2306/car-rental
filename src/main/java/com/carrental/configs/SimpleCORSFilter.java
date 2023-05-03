// package com.carrental.configs;

// import java.io.IOException;

// import org.springframework.context.annotation.Configuration;

// import jakarta.servlet.*;
// import jakarta.servlet.http.*;

// @Configuration
// public class SimpleCORSFilter implements Filter{

//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, accept, content-type");
//        chain.doFilter(req, res);
//    }

//    public void init(FilterConfig filterConfig) {
//    }

//    public void destroy() {
//    }
// }