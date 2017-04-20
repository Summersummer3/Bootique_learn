package com.summer;
import com.google.inject.Inject;
import io.bootique.annotation.Args;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by summer on 2017/3/27.
 */

@Path("/")
public class HelloResource {
    @Inject
    @Args
    private String[] args;

//    @GET
//    public String hello(){
//        String allArgs = Arrays.asList(args).stream().collect(joining(" "));
//        return "hello, world! The app was started with the following arguments: " + allArgs;
//    }
    @GET
    public Response sayHelloWorld(){
        return Response.ok("hello!!").type(MediaType.TEXT_PLAIN_TYPE).build();
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

    @POST
    @Consumes("text/plain")
    @Path("/echo")
    public String sayHelloEcho(String message){
        return "Hello! "+message;
    }

    private static Map<Integer, JsonTest> tests = new HashMap<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/save")
    public MyAnswer saveName(JsonTest t){
        tests.put(t.getId(), t);
        MyAnswer res = new MyAnswer();
        res.setMessage("OK");
        return res;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getTheName(@PathParam("id") int id){
        JsonTest test = tests.get(id);
        if(test == null){
            test = new JsonTest();
            test.setId(id);
            test.setName("Unknown " + Integer.toString(id));
        }
        MyAnswer ma = new MyAnswer();
        ma.setMessage("Ok, name:" + test.getName());
        return Response.status(200).type(MediaType.APPLICATION_JSON).entity(ma).build();
    }


}

class JsonTest{
    private int id;
    private String name;
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

}

class MyAnswer{
    public String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}