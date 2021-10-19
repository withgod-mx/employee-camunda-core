package kz.dar.summerintership.camunda.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CamundaTask {

    private String taskId;
    private String processInstanceId;
    private String processDefinitionName;

}
