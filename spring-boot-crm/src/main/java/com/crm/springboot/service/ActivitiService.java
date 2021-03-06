package com.crm.springboot.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import com.crm.springboot.pojos.FormField;
import com.crm.springboot.pojos.GroupManager;
import com.crm.springboot.pojos.ProcessBean;
import com.crm.springboot.pojos.ProcessVO;
import com.crm.springboot.pojos.activiti.TaskEntity;
import com.crm.springboot.pojos.user.User;
import com.github.pagehelper.PageInfo;

public interface ActivitiService extends BaseActivitiService{

	
	/**
	 * ********************将数据封装成分页的对象 pageinfo*******************
	 */
	PageInfo getPageInfo(List<?> list,long totalSize);
	
	 //同步act_id_membership和groupmanager表格的内容
	void asnyMembership(GroupManager groupManager);
	
   /**
    * *********************************申请列表****************************************
    */
	
	long countUserApplyList(Serializable assignee);
//	List<Task> getUserApplyList(Serializable assignee, Integer pageIndex, Integer pageSize);
	// 读取请假申请

	/**
	 * ********************************流程实例查询*************************************
	 */
	long countAllProcessInstance();
	List<ProcessInstance> selectAllProcessInstances(Integer pageIndex, Integer pageSize);
	PageInfo selectAllProcessInstancesPageInfo(Integer pageIndex, Integer pageSize);
	List<ProcessVO> createProcessVOs(List<ProcessInstance> processInstances);

	/**
	 * ********************************历史流程实例查询*************************************
	 */
	long countAllHistoryProcessInstance();
	List<HistoricProcessInstance> selectAllHistoryProcessInstances(Integer pageIndex, Integer pageSize);
	PageInfo selectAllHistoryProcessInstancesPageInfo(Integer pageIndex, Integer pageSize);
	List<ProcessVO> createHistoricProcessVOs(List<HistoricProcessInstance> historicProcessInstances);
	/**
	 * 
	 * *********************************task****************
	 */
	Task selectTask(String processInstanceId,String taskDefinitionKey);
	void setTaskCandidateGroup(String processInstanceId,String taskDefinitionKey,String delegateTaskName);
	//召回任务
	void withdrawTask(String processInstanceId,String userId);
	boolean canIwithdrawTask(String processInstanceId,String userId);
   /**
    * *********************************待办业务列表****************************************
    */
	
	long countAllTaskList();
	List<Task> selectAllTask(Integer pageIndex, Integer pageSize);
	PageInfo selectAllTaskPageInfo(Integer pageIndex, Integer pageSize);
	long countUserTaskList(Serializable userId);
	List<Task> getTaskToDoList(Serializable userId, Integer pageIndex, Integer pageSize);
	//查询待办的全部任务
    List<Task> listCandidateTasks(String userId,List<String> processInstanceIds,Integer pageIndex, Integer pageSize);
    long countListCandidateTasks(String userId,List<String> processInstanceIds);
    PageInfo listCandidatePageInfo(String userId,List<String> processInstanceIds,Integer pageIndex, Integer pageSize);
   // 查询用户所受理的全部任务
 	List<Task> listAssigneeTasks(String userId,Integer pageIndex, Integer pageSize);
 	long countListAssigneeTasks(String userId);
 	PageInfo listAssigneePageInfo(String userId,Integer pageIndex, Integer pageSize);
 	//查询受理的候选组的任务
 	List<Task> listCandidateAndAssigneeTasks(String userId,List<String> processInstanceIds,Integer pageIndex, Integer pageSize);
 	long countListCandidateAndAssigneeTasks(String userId,List<String> processInstanceIds);
 	PageInfo listCandidateAndAssigneePageInfo(String userId,List<String> processInstanceIds,Integer pageIndex, Integer pageSize);
 // 将Task集合转为TaskVO集合
 	List<ProcessBean> createTaskVOList(List<Task> tasks);
 	//查询一个任务所在流程的全部评论
 	
 	List<Comment> getComments(String processInstanceId);
    //查询当前任务的审批用户组
 	List<String> selectCandidateGroup(String taskId);
 	/**
	 * ********************************历史任务查询*************************************
	 */
 	long countAllHistoricTaskInstancesByUserId(String userId);
 	long countAllHistoricTaskInstancesByUserId(String userId,List<String> processInstanceIds);
 	List<HistoricTaskInstance> listAllHistoricTaskInstancesByUserId(String userId,List<String> processInstanceIds,Integer pageIndex, Integer pageSize);
 	PageInfo listAllHistoricTaskInstancesPageInfoByUserId(String userId,List<String> processInstanceIds,Integer pageIndex, Integer pageSize);
 	//分别查询通过和驳回的历史任务
 	long countAllHistoricTaskInstancesByUserIdWithPassValue(String userId,String isPass);
 	List<HistoricTaskInstance> listAllHistoricTaskInstancesByUserIdWithPassValue(String userId,Integer pageIndex, Integer pageSize,String isPass);
 	PageInfo listAllHistoricTaskInstancesPageInfoByUserIdWithPassValue(String userId,Integer pageIndex, Integer pageSize,String isPass);
 	
 	List<ProcessBean> createAllHistoricProcess(List<HistoricTaskInstance> historicTaskInstances);
 	
 	HistoricTaskInstance selectSingleHistoricTaskInstance(String userId,String processInstanceId);
 	
 	void updateTaskInst(TaskEntity taskEntity);
 	//查询历史任务平均耗时
 	String selectAVGDration(HashMap<String, Object> params);
 	//查询总共耗时
 	String selectSumDuration(HashMap<String, Object> params);
 	List<String> generateMonthLabels(int number);

}
