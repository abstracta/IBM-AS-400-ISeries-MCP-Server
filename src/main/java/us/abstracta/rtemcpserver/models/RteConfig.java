package us.abstracta.rtemcpserver.models;

import com.blazemeter.jmeter.rte.core.Protocol;
import com.blazemeter.jmeter.rte.core.TerminalType;
import com.blazemeter.jmeter.rte.core.ssl.SSLType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import us.abstracta.rtemcpserver.yamlloader.deserializer.ProtocolDeserializer;
import us.abstracta.rtemcpserver.yamlloader.deserializer.SslTypeDeserializer;
import us.abstracta.rtemcpserver.yamlloader.deserializer.TerminalTypeDeserializer;

public class RteConfig {

  private String server;
  private int port;
  @JsonDeserialize(using = ProtocolDeserializer.class)
  private Protocol protocol;
  @JsonDeserialize(using = TerminalTypeDeserializer.class)
  private TerminalType terminalType;
  @JsonDeserialize(using = SslTypeDeserializer.class)
  private SSLType sslType;

  public String getServer() {
    return server;
  }

  public void setServer(String server) {
    this.server = server;
  }

  public int getPort() {
    return port;
  }

  public Protocol getProtocol() {
    return protocol;
  }

  public void setProtocol(Protocol protocol) {
    this.protocol = protocol;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public TerminalType getTerminalType() {
    return terminalType;
  }

  public void setTerminalType(TerminalType terminalType) {
    this.terminalType = terminalType;
  }

  public SSLType getSslType() {
    return sslType;
  }

  public void setSslType(SSLType sslType) {
    this.sslType = sslType;
  }
}
