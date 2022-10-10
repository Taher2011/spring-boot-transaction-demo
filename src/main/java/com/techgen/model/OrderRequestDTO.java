package com.techgen.model;

import com.techgen.entity.Order;
import com.techgen.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDTO {

    private Order order;
    private Payment payment;
}
