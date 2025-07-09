package us.abstracta.rtemcpserver.service;

import com.blazemeter.jmeter.rte.core.RteProtocolClient;
import com.blazemeter.jmeter.rte.core.exceptions.RteIOException;
import com.blazemeter.jmeter.rte.core.wait.SyncWaitCondition;
import com.blazemeter.jmeter.rte.core.wait.WaitCondition;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.abstracta.rtemcpserver.models.RteConfig;
import us.abstracta.rtemcpserver.models.RteStep;

public class RteUtils {

  private static final int CONNECTION_TIMEOUT_MILLIS = 5000;
  private static final long TIMEOUT_MILLIS = 60000;
  private static final long STABLE_TIMEOUT_MILLIS = 1000;
  private static final Logger logger = LoggerFactory.getLogger(RteUtils.class);

  public static RteProtocolClient connect(RteConfig config, String server, int port)
      throws RteIOException, InterruptedException, TimeoutException {
    RteProtocolClient client = config.getProtocol().createProtocolClient();
    logger.debug(
        "Connecting to RTE server: {}:{} with protocol: {}, terminal type: {}, SSL type: {}",
        server, port, config.getProtocol(), config.getTerminalType(),
        config.getSslType());
    client.connect(config.getServer(), config.getPort(), config.getSslType(),
        config.getTerminalType(), CONNECTION_TIMEOUT_MILLIS);
    client.await(getWaitConditions());
    return client;
  }

  public static String executeSteps(RteProtocolClient client, List<RteStep> steps)
      throws RteIOException, InterruptedException, TimeoutException {
    for (RteStep step : steps) {
      logger.debug("Executing step: {}", step);
      client.send(step.getInputs(), step.getAttentionKey(), 0);
      client.await(getWaitConditions());
    }
    return client.getScreen().getText();
  }

  static List<WaitCondition> getWaitConditions() {
    return Collections.singletonList(new SyncWaitCondition(TIMEOUT_MILLIS, STABLE_TIMEOUT_MILLIS));
  }
}
