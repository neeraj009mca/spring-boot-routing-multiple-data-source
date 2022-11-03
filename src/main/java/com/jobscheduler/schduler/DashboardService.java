package com.jobscheduler.schduler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

	/*
	 * @Autowired TodoRepository todoRepository;
	 */
	
//	@Autowired
//	TopicRepository topicRepository;

	@Scheduled(cron = "*/5 * * * * *")
	public void computePrice() throws InterruptedException {
		Random random = new Random();
		double price = random.nextDouble() * 100;
		System.out.println("computing price at " + price + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
		Thread.sleep(4000);
		//shouldSaveTodoToTodoDB();
		//shouldSaveTopicToTopicDB();
	}

//	void shouldSaveTodoToTodoDB() {
//		Todo todo = new Todo("test");
//		Todo saved = todoRepository.save(todo);
//		Optional<Todo> result = todoRepository.findById(saved.id);
//		System.out.println("Todo Result= " + result.toString());
//	}

//	void shouldSaveTopicToTopicDB() {
//		Topic topic = new Topic("test");
//		Topic saved = topicRepository.save(topic);
//		Optional<Topic> result = topicRepository.findById(saved.id);
//		System.out.println("Topic Result= " + result.toString());
//	}

	private long delay = 0;
	public long getDelay() {
		this.delay += 1000;
		System.out.println("delaying " + this.delay + " milliseconds...");
		return this.delay;
	}

	public void process() {
		final long now = System.currentTimeMillis() / 1000;
		System.out.println("schedule tasks with dynamic delay - " + now);
	}
}
