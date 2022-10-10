package com.techgen.service.impl;

import com.techgen.entity.Order;
import com.techgen.entity.Payment;
import com.techgen.exception.PaymentException;
import com.techgen.model.OrderRequestDTO;
import com.techgen.model.OrderResponseDTO;
import com.techgen.repository.OrderRepository;
import com.techgen.repository.PaymentRepository;
import com.techgen.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional // if @Transactional not used then order will get saved and payment may not be saved if any exception occurred , order won't be rollback then
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {

        Order order = orderRequestDTO.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequestDTO.getPayment();

        if (!payment.getType().equals("DEBIT")) {
            throw new PaymentException("Payment card type does not support");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponseDTO.setStatus(order.getStatus());
        orderResponseDTO.setMessage("SUCCESS");
        return orderResponseDTO;
    }
}
