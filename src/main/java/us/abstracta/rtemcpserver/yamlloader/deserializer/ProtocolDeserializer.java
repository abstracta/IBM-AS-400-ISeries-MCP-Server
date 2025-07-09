package us.abstracta.rtemcpserver.yamlloader.deserializer;

import com.blazemeter.jmeter.rte.core.Protocol;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtocolDeserializer extends JsonDeserializer<Protocol> {

  private static final Logger logger = LoggerFactory.getLogger(ProtocolDeserializer.class);

  @Override
  public Protocol deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String text = p.getText();
    if (text == null || text.isBlank()) {
      logger.error("Unknown Protocol: {}", text);
      throw new IllegalArgumentException("Unknown SSLType: " + text);
    }
    for (Protocol candidate : Protocol.values()) {
      if (candidate.name().equalsIgnoreCase(text) ||
          candidate.toString().equalsIgnoreCase(text)) {
        return candidate;
      }
    }
    logger.error("Unknown Protocol: {}", text);
    throw new IllegalArgumentException("Unknown SSLType: " + text);
  }

}
