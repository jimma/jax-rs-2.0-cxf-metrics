package com.example.config;

import java.util.Arrays;
import java.util.Collections;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.codahale.CodahaleMetricsProvider;
import org.apache.cxf.tracing.opentracing.OpenTracingFeature;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.codahale.metrics.MetricRegistry;
import com.example.rs.PeopleRestService;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.uber.jaeger.samplers.ConstSampler;
import com.uber.jaeger.senders.HttpSender;

import io.opentracing.Tracer;

@Configuration
@EnableAutoConfiguration
@Import(PrometheusConfig.class)
public class AppConfig {
    @Bean(destroyMethod = "destroy")
    public Server jaxRsServer(final Bus bus) {
        final Tracer tracer = new com.uber.jaeger.Configuration("tracer-server",
                new com.uber.jaeger.Configuration.SamplerConfiguration(ConstSampler.TYPE, 1), /* or any other Sampler */
                new com.uber.jaeger.Configuration.ReporterConfiguration(
                        new HttpSender("http://localhost:19090/api/traces")) /* or any other Sender */
        ).getTracer();
        
        final JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();

        factory.setServiceBean(peopleRestService());
        factory.setProviders(Arrays.asList(new JacksonJsonProvider(), new OpenTracingFeature(tracer)));
        factory.setBus(bus);
        factory.setAddress("/");
        factory.setFeatures(Collections.singletonList(new MetricsFeature(new CodahaleMetricsProvider(bus))));
        factory.setProperties(Collections.singletonMap("org.apache.cxf.management.service.counter.name", "cxf-services."));
        
        return factory.create();
    }


    @Bean
    public PeopleRestService peopleRestService() {
        return new PeopleRestService(metricRegistry());
    }

    @Bean
    public MetricRegistry metricRegistry() {
        return new MetricRegistry();
    }
}
