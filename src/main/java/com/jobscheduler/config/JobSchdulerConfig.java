package com.jobscheduler.config;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.jobscheduler.schduler.DashboardService;

@Configuration
public class JobSchdulerConfig implements SchedulingConfigurer {

	@Autowired
	private DashboardService dashboardService;

	@Bean
	public Executor taskExecutor() {
		return Executors.newSingleThreadScheduledExecutor();
	}
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		System.out.println("========================");
		taskRegistrar.setScheduler(taskExecutor());
		taskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				dashboardService.process();
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext context) {
				Optional<Date> lastCompletionTime = Optional.ofNullable(context.lastCompletionTime());
				Instant nextExecutionTime = lastCompletionTime.orElseGet(Date::new).toInstant()
						.plusMillis(dashboardService.getDelay());
				return Date.from(nextExecutionTime);
			}
		});
	}
}
