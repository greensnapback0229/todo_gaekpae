package com.example.todo_list_gaekpae.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_list_gaekpae.controller.dto.request.CreateReq;
import com.example.todo_list_gaekpae.controller.dto.request.DeleteReq;
import com.example.todo_list_gaekpae.controller.dto.request.UpdateReq;
import com.example.todo_list_gaekpae.controller.dto.response.TodoListRes;
import com.example.todo_list_gaekpae.domain.Response;
import com.example.todo_list_gaekpae.domain.entity.Todo;
import com.example.todo_list_gaekpae.domain.entity.UserInfo;
import com.example.todo_list_gaekpae.domain.enums.ResponseCode;
import com.example.todo_list_gaekpae.domain.repository.TodoRepository;
import com.example.todo_list_gaekpae.security.SecurityUtil;
import com.example.todo_list_gaekpae.util.exception.BusinessException;

import jakarta.transaction.Transactional;

@Service
public class TodoService {

	private final TodoRepository todoRepository;
	private final SecurityUtil securityUtil;


	TodoService(TodoRepository todoRepository, SecurityUtil securityUtil){
		this.securityUtil = securityUtil;
		this.todoRepository = todoRepository;
	}

	@Transactional
	public Response<Void> createTodo(CreateReq req){
		System.out.println("[TodoService] - req_LocalDate=" + req.getEndDate());
		UserInfo userInfo = securityUtil.getUserInfo();
		Todo todoEntity = CreateReq.toEntity(req, userInfo);

		System.out.println("[TodoService] - todo_LocalDate=" + todoEntity.getEndDate());
		todoRepository.save(todoEntity);
		return Response.ok();
	}


	@Transactional
	public Response<Void> deleteTodo(DeleteReq req){
		Long todoId = req.getTodoId();

		try{
			todoRepository.deleteById(todoId);
			return Response.ok();
		} catch (Exception e){
			e.printStackTrace();

			throw new BusinessException();
		}
	}

	public Response<TodoListRes> getMonthTodo(Integer year, Integer month){
		UserInfo userInfo = securityUtil.getUserInfo();
		Long userInfoId = userInfo.getId();
		TodoListRes res;

		YearMonth yearMonth  = YearMonth.from(LocalDate.of(year, Month.of(month), 1));
		LocalDate startDate = yearMonth.atDay(1);
		LocalDate endDate = yearMonth.atEndOfMonth();

		List<Todo> todoList = todoRepository.findTodosByUserInfoIdAndDateRange(userInfoId, startDate, endDate)
			.orElseThrow(() -> new  BusinessException(ResponseCode.TODO_IS_EMPTY));

 		res = TodoListRes.fromEntityList(todoList);

		return Response.ok(res);
	}

	@Transactional
	public Response updateTodo(UpdateReq req){
		Long todoId = req.getTodoId();

		Todo todo = todoRepository.findById(todoId).orElseThrow( () ->
			new BusinessException(ResponseCode.TODO_ID_NOT_AVAILABLE)
		);

		todo.updateDone(req.getDone());

		return Response.ok();
	}



}
