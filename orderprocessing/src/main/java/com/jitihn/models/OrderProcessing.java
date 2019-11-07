package com.jitihn.models;

import com.jitihn.models.PreparationState.State;

public class OrderProcessing {
    private Order order;
    private State state;

    private String deliveryBoy;

    public OrderProcessing(OrderProcessing orderProcessing, String deliveryBoy) {
        this.order = orderProcessing.order;
        this.deliveryBoy = deliveryBoy;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDeliveryBoy() {
        return deliveryBoy;
    }

    public void setDeliveryBoy(String deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
    }

    public OrderProcessing() {
    }

}