package com.ucloudlink.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 定长线程池，支持定时及周期性任务执行。延迟执行
 * @author xiaobin.ma
 */
public class TestScheduledThreadPool {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() {
		    @Override
		    public void run() {
		        System.out.println("delay 3 seconds");
		    }
		}, 3, TimeUnit.SECONDS);
		
		
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("delay 1 seconds, and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}

}
