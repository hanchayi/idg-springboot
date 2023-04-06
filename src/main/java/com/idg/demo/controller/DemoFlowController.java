package com.idg.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idg.demo.service.DemoFlowService;

@RestController
public class DemoFlowController {

    @Autowired
    private DemoFlowService demoFlowService;

    @Autowired
    private RepositoryService repositoryService;


    @PostMapping("/process")
    public void startProcessInstance() {
        demoFlowService.startProcess();
    }

    @GetMapping("/tasks")
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = demoFlowService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @GetMapping("/deploys")
    public List<DeployItem> getDeploys() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        List<DeployItem> deploys = new ArrayList<DeployItem>();

        for (ProcessDefinition processDefinition : list) {
            deploys.add(new DeployItem(processDefinition.getId(), processDefinition.getName()));
        }
        return deploys;
    }

    @PostMapping("/deploy")
    public long deploy() {
        // 从前端接收到的XML字符串
        // 此处省去xml具体内容，可参考2.1的single-task.bpmn20.xml示例
        // 注意将id定义为singleTask2，以便跟2.1的内容作出区别
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions\n        xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"\n        xmlns:flowable=\"http://flowable.org/bpmn\"\n        targetNamespace=\"Examples\">\n\n    <process id=\"singleTask\" name=\"The One Task Process\">\n        <startEvent id=\"theStart\" />\n        <sequenceFlow id=\"flow1\" sourceRef=\"theStart\" targetRef=\"theTask\" />\n        <userTask id=\"theTask\" name=\"my task\" flowable:assignee=\"zhangsan\" />\n        <sequenceFlow id=\"flow2\" sourceRef=\"theTask\" targetRef=\"theEnd\" />\n        <endEvent id=\"theEnd\" />\n    </process>\n\n</definitions>";
        // 创建部署构建器
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        // 执行部署
        deploymentBuilder.addString("single-task2.bpmn20.xml", text).deploy();
        // 验证部署
        long count = repositoryService.createProcessDefinitionQuery().processDefinitionKey("singleTask").count();
        // count等于1，则说明部署成功
        return count;
    }

    @DeleteMapping("/deploy/{id}")
    public boolean deleteDeploy(@PathVariable String id) {
        repositoryService.deleteDeployment(id, true);
        return true;
    }

    @GetMapping("/deploy-xml")
    public void deployImage(HttpServletResponse response) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
            .processDefinitionKey("singleTask")
            .singleResult();

        String diagramResourceName = processDefinition.getResourceName();
        InputStream imageStream = repositoryService.getResourceAsStream(
            processDefinition.getDeploymentId(), diagramResourceName);
        
        this.writeFile(response, imageStream);
    }

    public void writeFile(HttpServletResponse resp, InputStream inputStream) {
		OutputStream out = null;
		try {
			out = resp.getOutputStream();
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = inputStream.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

    static class DeployItem {

        private String id;
        private String name;

        public DeployItem(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }

    static class TaskRepresentation {

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }

}
