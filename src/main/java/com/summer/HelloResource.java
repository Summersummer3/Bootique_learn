package com.summer;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
/**
 * Created by summer on 2017/3/27.
 */

@Path("/")
public class HelloResource {
    @GET
    public String hello(){
        return "hello, world!";
    }
}
