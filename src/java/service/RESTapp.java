package service;

import jakarta.ws.rs.core.Application;
import java.util.Set;

@jakarta.ws.rs.ApplicationPath("webresources")
public class RESTapp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        
        resources.add(ArticleFacadeREST.class);
        resources.add(CustomerFacadeREST.class);
        return resources;
    }
    
}
