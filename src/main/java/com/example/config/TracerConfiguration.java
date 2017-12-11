package com.example.config;

import java.util.regex.Pattern;

import javax.servlet.ServletContextEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.opentracing.contrib.metrics.prometheus.PrometheusMetricsReporter;

@Configuration
public class TracerConfiguration implements javax.servlet.ServletContextListener {

    @Bean
    public io.opentracing.Tracer tracer() {
        return io.opentracing.contrib.metrics.Metrics.decorate(
            io.opentracing.contrib.tracerresolver.TracerResolver.resolveTracer(),
            PrometheusMetricsReporter.newMetricsReporter()
                .withBaggageLabel("transaction","n/a")
                .build());
    }

    @Override
    public void contextInitialized(javax.servlet.ServletContextEvent sce) {
        sce.getServletContext().setAttribute(io.opentracing.contrib.web.servlet.filter.TracingFilter.SKIP_PATTERN, Pattern.compile("/metrics"));
        sce.getServletContext().setAttribute(io.opentracing.contrib.web.servlet.filter.TracingFilter.SKIP_PATTERN, Pattern.compile("/prometheus"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
       
    }
}