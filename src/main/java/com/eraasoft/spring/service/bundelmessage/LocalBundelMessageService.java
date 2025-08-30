package com.eraasoft.spring.service.bundelmessage;

import com.eraasoft.spring.help.BundelMessage;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@AllArgsConstructor
public class LocalBundelMessageService {
    private ResourceBundleMessageSource messageSource;

//    public LocalBundelMessageService(ResourceBundleMessageSource messageSource) {
//        this.messageSource = messageSource;
//    }
   public String  getMessageAR(String key){
        return messageSource.getMessage(key,null, new Locale("AR"));
   }
    public String  getMessageEN(String key){
        return messageSource.getMessage(key,null, new Locale("EN"));
    }
    public String getMessage(String key){
        return messageSource.getMessage(key,null, LocaleContextHolder.getLocale());
    }
    public BundelMessage getMessageAREN(String key){
        return new BundelMessage(getMessageAR(key), getMessageEN(key),null);
    }
}
