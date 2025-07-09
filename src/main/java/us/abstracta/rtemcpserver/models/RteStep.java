package us.abstracta.rtemcpserver.models;

import com.blazemeter.jmeter.rte.core.AttentionKey;
import com.blazemeter.jmeter.rte.core.Input;
import com.blazemeter.jmeter.rte.core.LabelInput;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import us.abstracta.rtemcpserver.yamlloader.deserializer.AttentionKeyDeserializer;

public class RteStep {

  private List<RteAction> actions;
  @JsonDeserialize(using = AttentionKeyDeserializer.class)
  private AttentionKey attentionKey;

  public List<RteAction> getActions() {
    return actions;
  }

  public void setActions(List<RteAction> actions) {
    this.actions = actions;
  }

  public AttentionKey getAttentionKey() {
    return attentionKey;
  }

  public void setAttentionKey(AttentionKey attentionKey) {
    this.attentionKey = attentionKey;
  }

  public List<Input> getInputs() {
    return actions.stream()
        .map(a -> (Input) new LabelInput(a.getLabel(), a.getInput()))
        .toList();
  }

  @Override
  public String toString() {
    return "RteStep{" +
        "actions=" + actions +
        ", attentionKey=" + attentionKey +
        '}';
  }
}
