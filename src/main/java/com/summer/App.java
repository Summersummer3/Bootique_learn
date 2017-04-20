package com.summer;

import io.bootique.Bootique;
import com.google.inject.Module;
import io.bootique.jersey.JerseyModule;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args)
    {

        Module module = binder -> JerseyModule.contributeResources(binder).addBinding().to(HelloResource.class);
        Bootique.app(args).module(module).autoLoadModules().run();
    }
}
