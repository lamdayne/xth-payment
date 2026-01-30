package com.xth.xthpayment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    CATEGORY_NOT_FOUND(1005, "Category id not found", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(1006, "Product id not found", HttpStatus.NOT_FOUND),
    FILE_REQUIRED(1007, "File not found",  HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(1008, "Order not found",  HttpStatus.NOT_FOUND),
    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
