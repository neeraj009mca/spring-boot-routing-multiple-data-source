package com.jobscheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobscheduler.topic.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
