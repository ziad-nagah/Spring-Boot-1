package com.eraasoft.spring.config;

import com.eraasoft.spring.controller.vm.ExceptionRecponse;
import com.eraasoft.spring.help.BundelMessage;
import com.eraasoft.spring.service.bundelmessage.LocalBundelMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionConfig {
private LocalBundelMessageService localBundelMessageService;
    @Autowired
    public ExceptionConfig(LocalBundelMessageService localBundelMessageService) {
        this.localBundelMessageService = localBundelMessageService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionRecponse> handelException(Exception exception){
        return ResponseEntity.badRequest().body(new ExceptionRecponse(exception.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<BundelMessage>> handelMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<BundelMessage>  exceptionRecponses =new ArrayList<>();

        exception.getBindingResult().getFieldErrors().stream().forEach(fieldError -> {
//            exceptionRecponses.add(new ExceptionRecponse(fieldError.getDefaultMessage()));
            BundelMessage message = new BundelMessage();
            message.setMessage(localBundelMessageService.getMessage(fieldError.getDefaultMessage()));
              exceptionRecponses.add(message);
//            exceptionRecponses.add(localBundelMessageService.getMessageAREN(fieldError.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(exceptionRecponses);
    }
}
