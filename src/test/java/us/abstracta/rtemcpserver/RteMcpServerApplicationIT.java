package us.abstracta.rtemcpserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.Content;
import io.modelcontextprotocol.spec.McpSchema.ListToolsResult;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes =
    RteMcpServerApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RteMcpServerApplicationIT {

  @LocalServerPort
  private int port;

  private McpSyncClient client;

  @BeforeAll
  void setupClient() {
    var transport = new WebFluxSseClientTransport(
        WebClient.builder().baseUrl("http://localhost:" + port));
    this.client = McpClient.sync(transport).build();
    this.client.initialize();
  }

  @AfterAll
  void tearDown() {
    this.client.closeGracefully();
  }

  @Test
  @DisplayName("Should return executeRteTransaction in the list of tools")
  void shouldReturnExpectedTool() {
    ListToolsResult tools = client.listTools();
    assertTrue(tools.tools().stream().anyMatch(t -> t.name().equals("loginRte")));
  }

  @Test
  @DisplayName("Should execute the executeRteTransaction tool")
  void shouldExecuteTool() throws Exception {
    CallToolResult call = client.callTool(new CallToolRequest("loginRte", Map.of("server", "localhost", "port", 2324)));
    InputStream is = getClass().getResourceAsStream("/expectedScreen.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
    String expectedScreen = reader.lines().collect(Collectors.joining("\n"));
    Optional<Content> resultScreen = call.content().stream()
        .filter(c -> c instanceof TextContent textContent).findFirst();
    assertEquals(expectedScreen, ((TextContent) resultScreen.get()).text());
  }

}
