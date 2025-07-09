package us.abstracta.rtemcpserver.yamlloader.deserializer;

import com.blazemeter.jmeter.rte.core.AttentionKey;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttentionKeyDeserializer extends JsonDeserializer<AttentionKey> {

  private static final Logger logger = LoggerFactory.getLogger(AttentionKeyDeserializer.class);

  @Override
  public AttentionKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    String text = p.getText();
    if (text == null || text.isBlank()) {
      logger.error("Unknown AttentionKey: {}", text);
      throw new IllegalArgumentException("Unknown AttentionKey: " + text);
    }
    for (AttentionKey candidate : AttentionKey.values()) {
      if (candidate.name().equalsIgnoreCase(text) || candidate.toString().equalsIgnoreCase(text)) {
        return candidate;
      }
    }
    logger.error("Unknown AttentionKey: {}", text);
    throw new IllegalArgumentException("Unknown AttentionKey: " + text);
  }

}
