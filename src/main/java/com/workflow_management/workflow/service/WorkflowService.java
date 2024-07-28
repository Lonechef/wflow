package com.workflow_management.workflow.service;

import com.workflow_management.workflow.model.Workflow;
import com.workflow_management.workflow.model.WorkflowStep;
import com.workflow_management.workflow.repository.WorkflowRepository;
import com.workflow_management.workflow.repository.WorkflowStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkflowService {

    @Autowired
    private WorkflowRepository workflowRepository;

    @Autowired
    private WorkflowStepRepository workflowStepRepository;

    @Transactional
    public Workflow saveWorkflow(Workflow workflow) {
        // Save the workflow first
        Workflow savedWorkflow = workflowRepository.save(workflow);

        // Set the saved workflow to each step and save steps
        for (WorkflowStep step : workflow.getSteps()) {
            step.setWorkflow(savedWorkflow);
            workflowStepRepository.save(step);
        }

        return savedWorkflow;
    }

    public List<Workflow> findAll() {
        return workflowRepository.findAll();
    }
}
