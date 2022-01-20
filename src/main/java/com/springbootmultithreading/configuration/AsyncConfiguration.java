package com.springbootmultithreading.configuration;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfiguration.class);
	
	@Bean (name = "taskExecutor")
	public Executor taskExecutor()
	{
		LOGGER.debug("Creating Async Task Executor");
		ThreadPoolTaskExecutor threadPoolTaskExecutor=new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(2);
		threadPoolTaskExecutor.setMaxPoolSize(2);
		threadPoolTaskExecutor.setQueueCapacity(100);
		threadPoolTaskExecutor.setThreadNamePrefix("User Thread - ");
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;	
	}
}
