package com.jitihn;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Path;
import com.jitihn.models.Order;
import com.jitihn.models.OrderProcessing;
import com.jitihn.models.PreparationState;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@Path("/hello")
public class OrderProcess {

    private Jsonb jsonb = JsonbBuilder.create();
    private Random random = new Random();

    private String[] deliveryBoy = new String[] { "Ajith", "Rahul", "bob" };

    @Incoming("orders")
    @Outgoing("queue")
    public CompletionStage<String> prepare(String message) {
        OrderProcessing order = jsonb.fromJson(message, OrderProcessing.class);
        return makeIt(order).thenApply(processed_order -> PreparationState.getProcessingState(processed_order));
    }

    private CompletionStage<OrderProcessing> makeIt(OrderProcessing order) {
        return CompletableFuture.supplyAsync(() -> {
            prepare();
            int rnd = new Random().nextInt(deliveryBoy.length);
            return new OrderProcessing(order, deliveryBoy[rnd]);
        }, executor);
    }

    private Executor executor = Executors.newSingleThreadExecutor();

    private void prepare() {
        try {
            Thread.sleep(random.nextInt(5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}