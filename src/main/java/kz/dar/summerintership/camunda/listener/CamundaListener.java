package kz.dar.summerintership.camunda.listener;

import kz.dar.summerintership.camunda.feign.HistoryFeign;
import kz.dar.summerintership.camunda.model.CamundaTask;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.spring.boot.starter.event.TaskEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class CamundaListener {

    @Autowired
    HistoryFeign historyFeign;

    @TransactionalEventListener(condition = "#taskEvent.eventName=='create'",
            phase = TransactionPhase.AFTER_COMPLETION)
    public void taskEvent(TaskEvent taskEvent) {
        try {
            log.info("taskID: {}, Assignee: {}, createTime: {}, description: {}, executionId: {}, processInstanceId: {}, eventName: {}, name: {}",
                    taskEvent.getId(), taskEvent.getAssignee(), taskEvent.getCreateTime(), taskEvent.getDescription(),
                    taskEvent.getExecutionId(), taskEvent.getProcessInstanceId(), taskEvent.getEventName(), taskEvent.getName());
            //taskServiceImpl.taskEventHandler(taskEvent);

            CamundaTask task = new CamundaTask();
            task.setTaskId(taskEvent.getId());
            task.setProcessInstanceId(taskEvent.getProcessInstanceId());
            task.setProcessDefinitionName(taskEvent.getName());

            historyFeign.upsertHistory(task);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
