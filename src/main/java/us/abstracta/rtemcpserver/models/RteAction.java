package us.abstracta.rtemcpserver.models;

public class RteAction {

  private String label;
  private String input;

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  @Override
  public String toString() {
    return "RteAction{" +
        "label='" + label + '\'' +
        ", input='" + input + '\'' +
        '}';
  }
}
