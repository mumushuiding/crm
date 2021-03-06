package com.crm.springboot.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.crm.springboot.pojos.GroupManager;
import com.crm.springboot.pojos.GroupTable;
import com.crm.springboot.pojos.ProcessBean;


public interface BaseActivitiService {
	/**
	 * ********************************用户组管理*************************************
	 */
	
	List<Group> selectGroups(int nowPage,int pageSize);
	List<Group> selectGroups();
	Group selectGroupById(Serializable id);
	long GroupsCount();
	Group saveGroup(GroupTable groupTable);
	void updateGroup(GroupTable groupTable);
	void deleteGroup(Serializable id);
	//查询用户所在的所有用户组ids
	List<String> candidateGroups(String userId);
	
	/**
	 * ********************************用户管理*************************************
	 */
	
	void saveUser(Serializable id);
	void deleteUser(Serializable id);
	User selectUser(Serializable id);
	/**
	 * ********************************用户与用户组关系*************************************
	 */
	void bindUserAndGroup(String userId,String groupId);
	void bindUserAndGroups(String userId,String[] groupIds);
	void unBindUserAndGroup(String userId,String groupId);
	void unBindUserWithGroupids(String userid,String groupids);
	void unBindUserWithGroupids(String userid,List<String> groupids);
	
	/**
	 * ********************************流程定义存储与查询*************************************
	 */
    long processDefinitionCount();
	List<ProcessDefinition> selectAllProcessDefinition(int pageIndex,int pageSize);
	void viewProcessImage(Serializable deploymentId,String resourceName,OutputStream out);
	void showDiagram(InputStream is,OutputStream out);
	
	List<ProcessDefinition> selectAllProcessDefinitionsOrderByDesc();
	void deleteLowerVersionProcessDefinitions(boolean cascade);

	
	Deployment getDeploymentById(String deploymentId);
	void deleteDeploymentById(String deploymentId, boolean cascade);
	String getDeploymentIdByBusinessKey(String businessKey);
	/**
	 * ********************************流程实例**********************************
	 */
	void deleteProcessInstance(String processInstanceId,String deleteReason);
	ProcessInstance selectProcessInstance(String processInstanceId);
	Object getVariableFromProcessInstance(String processInstanceId,String variableName);
	Object getVariableFromHistoryWithProcessInstanceId(String processInstanceId,String variableName);
    /**
     * *********************************设置变量****************************************
     */
 	void setVariable(Task task,HashMap<String , Object> variable);
 	void setVariable(ProcessBean processBean,Object param,String paramname);
 	void setVariable(Task task,Object param,String paramname);
 	
	/**
	 * ********************************任务存储与查询*************************************
	 */
	Task getFirstTask(String processInstanceId);
	//查询task
    Task selectTask(String taskId);
	void setAssignee(String taskId,String userId);
	void deleteTaskById(String taskId);
	
    
	/**
	 * 根据taskId获取流程实例
	 * @param taskId
	 * @return
	 */
	ProcessInstance getProcessInstance(String taskId);

    /**
     * 根据流程实例id获取当前任务进度流程图
     * @param processInstanceId
     */
    public void getDiagram(String processInstanceId, String executionId, OutputStream out);
	
	/**
	 * 启动流程
	 * @param processId
	 * @param bizKey
	 */
	ProcessInstance startProcess(String processDefinitionKey,String deploymentId);
	/**
	 * 审批
	 * @param taskId 审批的任务id
	 * @param userId 审批人的id
	 * @param audit  审批意见：通过（pass）or驳回（reject）
	 */
	void completeWithCommentAndAudit(String taskId,String userId,String audit,String comment);
	void complete(String taskId,Map<String,Object> variables);
	void complete(String taskId);
	void complete(ProcessBean processBean);
	void addComment(String taskId,String comment,String userId);
	void claim(String taskId,String userId);
	

	
}
