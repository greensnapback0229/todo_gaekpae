package com.example.todo_list_gaekpae.domain.repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.todo_list_gaekpae.domain.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	@Query("SELECT t FROM Todo t WHERE t.userInfo.id = :userInfoId AND t.endDate BETWEEN :startDate AND :endDate ORDER BY t.endDate ASC")
	Optional<List<Todo>> findTodosByUserInfoIdAndDateRange(
		@Param("userInfoId") Long userInfoId,
		@Param("startDate") LocalDate startDate,
		@Param("endDate") LocalDate endDate
	);

	default Optional<List<Todo>> findTodosForCurrentMonth(Long userInfoId) {
		LocalDate now = LocalDate.now();
		YearMonth currentMonth = YearMonth.from(now);
		LocalDate startDate = currentMonth.atDay(1); // 이번 달의 첫날
		LocalDate endDate = currentMonth.atEndOfMonth(); // 이번 달의 마지막 날
		return findTodosByUserInfoIdAndDateRange(userInfoId, startDate, endDate);
	}

}
