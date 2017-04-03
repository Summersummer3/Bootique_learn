package com.summer;
import com.google.inject.Inject;
import io.bootique.annotation.Args;

import static java.util.stream.Collectors.joining;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}
