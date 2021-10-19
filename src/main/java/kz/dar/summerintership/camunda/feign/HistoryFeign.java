package kz.dar.summerintership.camunda.feign;

import kz.dar.summerintership.camunda.model.CamundaTask;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "camunda-history")
public interface HistoryFeign {

    @PostMapping("/history")
    public CamundaTask upsertHistory(@RequestBody CamundaTask task);

}
