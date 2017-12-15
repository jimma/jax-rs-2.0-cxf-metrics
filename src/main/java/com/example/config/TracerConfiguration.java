package com.example.config;

import java.util.regex.Pattern;

import javax.servlet.ServletContextEvent;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TracerConfiguration implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(javax.servlet.ServletContextEvent sce) {
        sce.getServletContext().setAttribute(io.opentracing.contrib.web.servlet.filter.TracingFilter.SKIP_PATTERN,
                Pattern.compile("/prometheus"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }
}