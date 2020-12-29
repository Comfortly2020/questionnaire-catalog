package com.comfortly.questionnairecatalog.api.v1.resources;

import com.comfortly.questionnairecatalog.lib.QuestionnaireData;
import com.comfortly.questionnairecatalog.services.beans.QuestionnaireDataBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/questionnaire")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionnaireDataResource {

    private Logger log = Logger.getLogger(QuestionnaireDataResource.class.getName());

    @Inject
    private QuestionnaireDataBean questionnaireDataBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getQuestionnaireData() {

        List<QuestionnaireData> questionnaireData = questionnaireDataBean.getQuestionnaireData();

        return Response.status(Response.Status.OK).entity(questionnaireData).build();
    }

    @POST
    public Response createQuestionData(@HeaderParam("UserId") String userId, QuestionnaireData questionnaireData) {

        if (userId == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Missing UserId header").build();
        }

        if (!userId.equals("admin")) {
            return Response.status(Response.Status.UNAUTHORIZED.getStatusCode(), "You are not authorized to create questions").build();
        }

        if ((questionnaireData.getQuestion() == null || questionnaireData.getType() == null || questionnaireData.getActive() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            questionnaireData = questionnaireDataBean.createQuestion(questionnaireData);
        }

        return Response.status(Response.Status.OK).entity(questionnaireData).build();
    }

    @PUT
    @Path("{questionId}")
    public Response updateQuestionData(@HeaderParam("UserId") String userId, @PathParam("questionId") Integer questionId, QuestionnaireData questionnaireData) {

        if (userId == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Missing UserId header").build();
        }

        if (!userId.equals("admin")) {
            return Response.status(Response.Status.UNAUTHORIZED.getStatusCode(), "You are not authorized to delete questions").build();
        }

        if ((questionnaireData.getQuestion() == null || questionnaireData.getType() == null || questionnaireData.getActive() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            QuestionnaireData updatedQuestionnaireData = questionnaireDataBean.putQuestionData(questionId, questionnaireData);

            if (updatedQuestionnaireData != null) {
                return Response.status(Response.Status.OK).entity(questionnaireData).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Question does not exist").build();
            }
        }
    }

    @DELETE
    @Path("{questionId}")
    public Response deleteQuestionData(@HeaderParam("UserId") String userId, @PathParam("questionId") Integer questionId) {

        if (userId == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Missing UserId header").build();
        }

        if (!userId.equals("admin")) {
            return Response.status(Response.Status.UNAUTHORIZED.getStatusCode(), "You are not authorized to delete questions").build();
        }

        boolean deleted = questionnaireDataBean.deleteQuestionData(questionId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
