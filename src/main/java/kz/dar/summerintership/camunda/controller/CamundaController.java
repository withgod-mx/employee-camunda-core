package kz.dar.summerintership.camunda.controller;

import kz.dar.summerintership.camunda.engine.CamundaEngine;
import org.camunda.bpm.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class CamundaController {

    @Autowired
    private CamundaEngine camundaEngine;

    @GetMapping("/healthcheck")
    public String helthCheck() {
        return "it's Work";
    }

    @PostMapping("/create")
    public Deployment createProcess() {
        return camundaEngine.createProcess();
    }


}
