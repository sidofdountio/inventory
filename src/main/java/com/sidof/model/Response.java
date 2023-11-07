package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @Author sidof
 * @Since 25/10/2023
 * @Version v1.0
 * @YouTube @sidof8065
 */
@Data @SuperBuilder @JsonInclude(NON_DEFAULT)
public class Response {

    protected  Map<?,?> data;
    protected  String message;
    protected  String developerMessage;
    protected  String reason;
    protected  HttpStatus status;
    protected  int statusCode;
    protected LocalDateTime timeStamp;


}
