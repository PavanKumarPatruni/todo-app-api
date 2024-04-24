package com.pavan.todo.controllers;

import com.pavan.todo.dto.ResponseDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class BaseController {

    private final MessageSource messageSource;

    public BaseController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<Object>> healthCheck() {
        return new ResponseDTO<>(HttpStatus.OK, messageSource.getMessage("api.running.message", null, "API Running...", LocaleContextHolder.getLocale()), null, "Health check").formatResponse();
    }

}