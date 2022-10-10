package com.techgen.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDTO {

    private String orderTrackingNumber;
    private String status;
    private String message;

}
