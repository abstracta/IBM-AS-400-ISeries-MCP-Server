package us.abstracta.rtemcpserver;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import us.abstracta.rtemcpserver.service.RteServiceLoginTool;

@SpringBootApplication
public class RteMcpServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(RteMcpServerApplication.class, args);
  }

  @Bean
  public ToolCallbackProvider rteTool(RteServiceLoginTool rteService) {
    return MethodToolCallbackProvider.builder().toolObjects(rteService).build();
  }
}
