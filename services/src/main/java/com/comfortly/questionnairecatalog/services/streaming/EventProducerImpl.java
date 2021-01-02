package com.comfortly.questionnairecatalog.services.streaming;

import com.comfortly.questionnairecatalog.config.StreamingProperties;
import com.kumuluz.ee.streaming.common.annotations.StreamProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
public class EventProducerImpl {

    private static final Logger log = Logger.getLogger(EventProducerImpl.class.getName());

    @Inject
    private StreamingProperties streamingProperties;

    @Inject
    @StreamProducer
    private Producer producer;

    public Response produceMessage(String userId, Integer tripId) {

        JSONObject obj = new JSONObject();
        obj.put("userId", userId);
        obj.put("tripId", tripId);

        ProducerRecord<String, String> record = new ProducerRecord<>(streamingProperties.getProcessingTopic(), tripId.toString(), obj.toString());

        producer.send(record,
                (metadata, e) -> {
                    if (e != null) {
                        e.printStackTrace();
                    } else {
                        log.info("The offset of the produced message record is: " + metadata.offset());
                    }
                });

        return Response.ok().build();
    }
}
