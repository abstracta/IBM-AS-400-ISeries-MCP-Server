package us.abstracta.rtemcpserver.yamlloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.abstracta.rtemcpserver.models.RteFlow;

public class RteYamlLoader {

  private static final Logger logger = LoggerFactory.getLogger(RteYamlLoader.class);

  public static RteFlow load(String flowPath) throws IOException {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    mapper.findAndRegisterModules();

    try (InputStream in = RteYamlLoader.class.getResourceAsStream(flowPath)) {
      if (in == null) {
        throw new FileNotFoundException("Can not find flow file: " + flowPath);
      }
      return mapper.readValue(in, RteFlow.class);
    }
  }
}
