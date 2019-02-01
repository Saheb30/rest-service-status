package org.saheb.client;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
	public static void main(String[] args) {
		Runnable task = new RestClient();
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
	}
}
