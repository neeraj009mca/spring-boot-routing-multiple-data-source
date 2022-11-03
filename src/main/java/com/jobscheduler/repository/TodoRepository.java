package com.jobscheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobscheduler.todo.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
