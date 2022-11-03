package com.jobscheduler.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.jobscheduler.repository.TodoRepository;
import com.jobscheduler.repository.TopicRepository;
import com.jobscheduler.todo.entity.Todo;
import com.jobscheduler.topic.entity.Topic;

@ActiveProfiles("multipledatasources")
@DataJpaTest // no test database!
class MultipleDatasourcesIntegrationTest {

	@Autowired
	TodoRepository todoRepo;
	@Autowired
	TopicRepository topicRepo;

	@Test
	void shouldSaveTodoToTodoDB() {
		Todo todo = new Todo("test");
		Todo saved = todoRepo.save(todo);
		Optional<Todo> result = todoRepo.findById(saved.id);
		assertThat(result).isPresent();
	}

	@Test
	void shouldSaveTopicToTopicDB() {
		Topic topic = new Topic("test");
		Topic saved = topicRepo.save(topic);
		Optional<Topic> result = topicRepo.findById(saved.id);
		assertThat(result).isPresent();
	}

}
