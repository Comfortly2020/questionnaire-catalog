package com.comfortly.questionnairecatalog.api.v1.resources;

import com.comfortly.questionnairecatalog.lib.AnswerData;
import com.comfortly.questionnairecatalog.services.beans.AnswerDataBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/answers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnswersDataResource {

    @Inject
    private AnswerDataBean answerDataBean;

    @GET
    @Path("/{tripDataId}")
    public Response getTripData(@HeaderParam("UserId") String userId, @PathParam("tripDataId") Integer tripDataId) {

        if (userId == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Missing UserId header").build();
        }

        List<AnswerData> answersData = answerDataBean.getAnswersData(userId, tripDataId);

        if (answersData == null || answersData.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(answersData).build();
    }

    @POST
    public Response createAnswersData(@HeaderParam("UserId") String userId, List<AnswerData> answerDataList) {

        if (userId == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), "Missing UserId header").build();
        } else {
            for (AnswerData answerData : answerDataList) {
                answerData.setUserId(userId);
                if (answerData.getTripId() == null || answerData.getQuestionId() == null || answerData.getQuestion() == null || answerData.getType() == null || answerData.getAnswer() == null || answerData.getTimestamp() == null) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
            }
        }

        answerDataBean.postAnswers(answerDataList);

        return Response.status(Response.Status.OK).build();

    }
}
