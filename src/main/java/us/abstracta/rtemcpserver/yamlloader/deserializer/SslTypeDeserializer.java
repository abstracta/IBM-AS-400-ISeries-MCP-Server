package us.abstracta.rtemcpserver.yamlloader.deserializer;

import com.blazemeter.jmeter.rte.core.ssl.SSLType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SslTypeDeserializer extends JsonDeserializer<SSLType> {

  private static final Logger logger = LoggerFactory.getLogger(SslTypeDeserializer.class);

  @Override
  public SSLType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String text = p.getText();
    if (text == null || text.isBlank()) {
      return SSLType.NONE;
    }
    for (SSLType candidate : SSLType.values()) {
      if (candidate.name().equalsIgnoreCase(text) || candidate.toString().equalsIgnoreCase(text)) {
        return candidate;
      }
    }
    logger.error("Unknown SSLType: {}", text);
    throw new IllegalArgumentException("Unknown SSLType: " + text);
  }
}
