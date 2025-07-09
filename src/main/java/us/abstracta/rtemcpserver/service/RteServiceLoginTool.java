package us.abstracta.rtemcpserver.service;

import com.blazemeter.jmeter.rte.core.RteProtocolClient;
import com.blazemeter.jmeter.rte.core.exceptions.RteIOException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import us.abstracta.rtemcpserver.models.RteFlow;
import us.abstracta.rtemcpserver.yamlloader.RteYamlLoader;

@Service
public class RteServiceLoginTool {

  private static final Logger logger = LoggerFactory.getLogger(RteServiceLoginTool.class);

  @Tool(description = "Execute an RTE transaction in a given server")
  public String loginRte(String server, int port) {
    RteProtocolClient client = null;
    try {
      RteFlow flow = RteYamlLoader.load("/flow.yaml");
      client = RteUtils.connect(flow.getConfig(), server, port);
      return RteUtils.executeSteps(client, flow.getSteps());

    } catch (IOException | RteIOException | InterruptedException | TimeoutException e) {
      logger.error("Error executing RTE flow", e);
      return "Error executing RTE flow: " + e.getMessage();
    } finally {
      if (client != null) {
        try {
          client.disconnect();
        } catch (RteIOException e) {
          logger.error("Error disconnecting RTE client", e);
        }
      }
    }
  }
}