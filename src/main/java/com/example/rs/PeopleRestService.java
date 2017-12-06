package com.example.rs;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import com.example.model.Person;

@Component
public class PeopleRestService {
	private final Random random = new Random();
	private Histogram hist;
	
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Person> getPeople() throws InterruptedException {
        long delay = random.nextInt(1200);
        if(delay > 1000) {
            throw new RuntimeException("Failed to find people");
        }
        this.hist.update(delay);
        Thread.sleep(delay);
        return Arrays.asList(new Person("a@b.com", "John", "Smith"), 
        		new Person("c@b.com", "Bob", "Bobinec"));
    }
    
    public PeopleRestService(MetricRegistry registry) {
        this.hist = registry.histogram("peopleReactionTime");
    }
}
