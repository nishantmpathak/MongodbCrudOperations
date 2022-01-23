package com.nishant.mongodb.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String pin;
}
