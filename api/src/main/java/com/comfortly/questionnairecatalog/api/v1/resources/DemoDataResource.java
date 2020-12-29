package com.comfortly.questionnairecatalog.api.v1.resources;

import com.comfortly.questionnairecatalog.config.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/demo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DemoDataResource {

    private Logger log = Logger.getLogger(DemoDataResource.class.getName());

    @Context
    protected UriInfo uriInfo;

    @Inject
    private RestProperties restProperties;

    @POST
    @Path("break")
    public Response makeUnhealthy() {

        restProperties.setBroken(true);

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("repair")
    public Response makeHealthy() {

        restProperties.setBroken(false);

        return Response.status(Response.Status.OK).build();
    }
}
