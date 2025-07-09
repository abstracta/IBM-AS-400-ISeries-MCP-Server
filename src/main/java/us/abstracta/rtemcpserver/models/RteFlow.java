package us.abstracta.rtemcpserver.models;

import java.util.List;

public class RteFlow {

  private RteConfig config;
  private List<RteStep> steps;

  public RteConfig getConfig() {
    return config;
  }

  public void setConfig(RteConfig config) {
    this.config = config;
  }

  public List<RteStep> getSteps() {
    return steps;
  }

  public void setSteps(List<RteStep> steps) {
    this.steps = steps;
  }
}
