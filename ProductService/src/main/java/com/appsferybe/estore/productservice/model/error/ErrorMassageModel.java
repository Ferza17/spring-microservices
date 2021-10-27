package com.example.productservice.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMassageModel {
    private final Date timestamp;
    private final String message;
}
