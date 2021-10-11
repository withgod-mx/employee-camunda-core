package kz.dar.summerintership.camunda.engine;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.GatewayDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.soap.SAAJResult;

@Component
public class CamundaEngine {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;



    public Deployment createProcess() {

        BpmnModelInstance modelInstance = Bpmn.createExecutableProcess("payments")
                .name("Payments for test")
                .startEvent()
                .name("Start Payment")
                .userTask()
                .name("Payment request")
                .camundaAssignee("ivanov")
                .userTask()
                .id("secureCheck")
                .name("Secure Check")
                .camundaAssignee("petrov")
                .exclusiveGateway()
                .name("approve?")
                .gatewayDirection(GatewayDirection.Diverging)
                .condition("approve", "${approved}")
                .userTask()
                .name("Pillar head check")
                .camundaAssignee("Pushkin")
                .camundaFormKey("embedded:app:forms/prepare-bank-transfer.html")
                .camundaCandidateGroups("accounting")
                .serviceTask()
                .name("send 1C")
                .camundaClass("org.camunda.bpm.example.invoice.service.ArchiveInvoiceService")
                .endEvent()
                .name("Finish")
                .moveToLastGateway()
                .condition("reject", "${!reject}")
                .userTask()
                .name("reject")
                .camundaAssignee("demo")
                .endEvent()
                .name("Finish")
                /*.exclusiveGateway()
                .name("Review successful?")
                .gatewayDirection(GatewayDirection.Diverging)
                .condition("no", "${!clarified}")
                .endEvent()
                .name("Invoice not processed")
                .moveToLastGateway()
                .condition("yes", "${clarified}")*/
                //.connectTo("secureCheck")
                .done();

        return processEngine.getRepositoryService().createDeployment().addModelInstance("invoice.bpmn", modelInstance).deploy();

    }

}
