package com.example.config;

import java.util.Collection;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.spring.boot.SpringBootMetricsCollector;

@Configuration
@Import(TracerConfiguration.class)
public class PrometheusConfig {

    @Bean
    public CollectorRegistry collectorRegistry() {
        return new CollectorRegistry();
    }

    @Bean
    public SpringBootMetricsCollector metricsCollector(final Collection<PublicMetrics> metrics, final CollectorRegistry registry) {
        return new SpringBootMetricsCollector(metrics).register(registry);
    }

    @Bean
    public ServletRegistrationBean exporterServlet(CollectorRegistry registry) {
        return new ServletRegistrationBean(new MetricsServlet(registry), "/prometheus");
    }

}
