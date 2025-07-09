package us.abstracta.rtemcpserver.yamlloader.deserializer;

import com.blazemeter.jmeter.rte.core.TerminalType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminalTypeDeserializer extends JsonDeserializer<TerminalType> {

  private static final Logger logger = LoggerFactory.getLogger(TerminalTypeDeserializer.class);

  @Override
  public TerminalType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String text = p.getText();
    try {
      return TerminalType.fromString(text);
    } catch (IllegalArgumentException e) {
      logger.error("Unknown TerminalType: {}", text, e);
      throw new IllegalArgumentException("Unknown TerminalType: " + text, e);
    }
  }
}
