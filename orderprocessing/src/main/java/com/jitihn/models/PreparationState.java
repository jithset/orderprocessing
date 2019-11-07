package com.jitihn.models;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

public class PreparationState {

    private Order order;
    private OrderProcessing orderProcessed;

    private State state;

    public enum State {
        IN_QUEUE, PROCESSING, COMPLETED;
    }

    private static final Jsonb JSON = JsonbBuilder.create();

    public static String getPreparationState(Order queOrder) {
        return JSON.toJson(new PreparationState().setOrder(queOrder).setState(State.IN_QUEUE));
    }

    public static String getProcessingState(OrderProcessing processOrder) {
        return JSON.toJson(new PreparationState().setOrderProcessed(processOrder).setState(State.PROCESSING));
    }

    public Order getOrder() {
        return order;
    }

    public PreparationState setOrder(Order order) {
        this.order = order;
        return this;
    }

    public State getState() {
        return state;
    }

    public PreparationState setState(State state) {
        this.state = state;
        return this;
    }

    public OrderProcessing getOrderProcessed() {
        return orderProcessed;
    }

    public PreparationState setOrderProcessed(OrderProcessing orderProcessed) {
        this.orderProcessed = orderProcessed;
        return this;
    }

}