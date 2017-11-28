package com.example.config;

import java.util.Collection;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.spring.boot.SpringBootMetricsCollector;

@Configuration
public class PrometheusConfig {

	@Bean
	public SpringBootMetricsCollector metricsCollector(final Collection<PublicMetrics> metrics) {
		return new SpringBootMetricsCollector(metrics).register();
	}

	@Bean
	public ServletRegistrationBean exporterServlet() {
		return new ServletRegistrationBean(new MetricsServlet(), "/prometheus");
	}
}
