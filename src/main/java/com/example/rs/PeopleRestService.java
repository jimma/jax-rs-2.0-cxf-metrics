package com.example.rs;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.example.model.Person;

import reactor.core.publisher.Flux;

@Path("/people")
@Component
public class PeopleRestService {
	private final Random random = new Random();
	private Histogram hist;
	
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Person> getPeople() {
        int delay = random.nextInt(1000);
        this.hist.update(delay);
        return Flux
        	.just(
        		new Person("a@b.com", "John", "Smith"), 
        		new Person("c@b.com", "Bob", "Bobinec")
        	)
        	.delayMillis(delay)
        	.toStream()
        	.collect(Collectors.toList());
    }
    
    public PeopleRestService(MetricRegistry registry) {
        this.hist = registry.histogram("peopleReactionTime");
    }
}
