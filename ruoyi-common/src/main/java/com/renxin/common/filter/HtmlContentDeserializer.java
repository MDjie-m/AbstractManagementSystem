package com.renxin.common.filter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class HtmlContentDeserializer extends JsonDeserializer<String> {
    
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = p.getText();
        log.debug("Deserializing field: original value = {}", value);

        // For example purposes, let's just strip out HTML tags
        value = value.replaceAll("<[^>]*>", "");
        log.debug("Deserializing field: cleaned value = {}", value);
        return value;
    }
    
}

