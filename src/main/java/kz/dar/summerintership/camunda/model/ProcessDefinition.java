package kz.dar.summerintership.camunda.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessDefinition {

    private String id;
    private String key;
    private String category;
    private String description;
    private String name;
    private int version;
    private int resource;
    private int deploymentId;
    private String diagram;
    private boolean suspended;
    private String tenantId;
    private String versionTag;
    private String historyTimeToLive;
    private boolean startableInTasklist;


    /**
     * {
     *         "id": "payments:1:187b22b9-2a86-11ec-bc72-0242a3e2da17",
     *         "key": "payments",
     *         "category": "http://www.omg.org/spec/BPMN/20100524/MODEL",
     *         "description": null,
     *         "name": "Payments for test",
     *         "version": 1,
     *         "resource": "invoice.bpmn",
     *         "deploymentId": "1863a317-2a86-11ec-bc72-0242a3e2da17",
     *         "diagram": null,
     *         "suspended": false,
     *         "tenantId": null,
     *         "versionTag": null,
     *         "historyTimeToLive": null,
     *         "startableInTasklist": true
     *     }
     */

}
