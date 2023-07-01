package com.hingehealth.demo.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class NodeSerializer extends StdSerializer<Node> {
    
    public NodeSerializer() {
        this(null);
    }
  
    public NodeSerializer(Class<Node> t) {
        super(t);
    }

    @Override
    public void serialize(
      Node node, JsonGenerator jgen, SerializerProvider provider) 
      throws IOException, JsonProcessingException {
 
        jgen.writeStartObject();
        jgen.writeObjectFieldStart(new Long(node.id).toString());
        jgen.writeStringField("label", node.label);
        jgen.writeObjectField("children", node.children);
        jgen.writeEndObject();
        jgen.writeEndObject();
    }
}
