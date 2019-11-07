package com.jitihn;

import java.util.UUID;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import com.jitihn.models.Order;
import com.jitihn.models.PreparationState;

@Path("/order")
public class OrderResource {

    @Inject
    Jsonb jsonb;

    @Inject
    @Channel("orders")
    Emitter<String> orders;

    @Inject
    @Channel("queue")
    Emitter<String> queue;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Order postOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        orders.send(jsonb.toJson(order));
        queue.send(getInQueueState(order));
        return order;
    }

    private String getInQueueState(Order order) {
        return PreparationState.getPreparationState(order);
    }
}