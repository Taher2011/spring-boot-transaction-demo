package com.techgen.service;

import com.techgen.model.OrderRequestDTO;
import com.techgen.model.OrderResponseDTO;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
}
