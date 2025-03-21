package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryQuery {

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAll(Pageable pageable);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.weather = :weather " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByWeather(Pageable pageable, String weather);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.modifiedAt >= :updatedStartAt " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByStartTime(Pageable pageable, LocalDateTime updatedStartAt);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.modifiedAt <= :updatedEndAt " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByEndTime(Pageable pageable, LocalDateTime updatedEndAt);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.weather = :weather AND t.modifiedAt >= :updatedStartAt " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByWeatherAndStartTime(Pageable pageable, String weather, LocalDateTime updatedStartAt);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.weather = :weather AND t.modifiedAt <= :updatedEndAt " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByWeatherAndEndTime(Pageable pageable, String weather, LocalDateTime updatedEndAt);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.modifiedAt >= :updatedStartAt AND t.modifiedAt <= :updatedEndAt " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByStartTimeAndEndTime(Pageable pageable, LocalDateTime updatedStartAt, LocalDateTime updatedEndAt);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN FETCH t.user u " +
            "WHERE t.weather = :weather AND t.modifiedAt >= :updatedStartAt AND t.modifiedAt <= :updatedEndAt " +
            "ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByWeatherAndStartTimeAndEndTime(Pageable pageable, String weather, LocalDateTime updatedStartAt, LocalDateTime updatedEndAt);

//    @Query("SELECT t FROM Todo t " +
//            "LEFT JOIN t.user " +
//            "WHERE t.id = :todoId")
//    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);
}
