package com.summer;
import com.google.inject.Inject;
import io.bootique.annotation.Args;

import static java.util.stream.Collectors.joining;

import javax.ws.rs.*;
import java.util.Arrays;

/**
 * Created by summer on 2017/3/27.
 */

@Path("/")
public class HelloResource {
    @Inject
    @Args
    private String[] args;

    @GET
    public String hello(){
        String allArgs = Arrays.asList(args).stream().collect(joining(" "));
        return "hello, world! The app was started with the following arguments: " + allArgs;
    }

    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name, @QueryParam("count")
        @DefaultValue("1") int count){
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < count;i++){
            sb.append("Hello, "+ name  + " " + Integer.toString(i) + "\n" );
        }
        return sb.toString();
    }

}
