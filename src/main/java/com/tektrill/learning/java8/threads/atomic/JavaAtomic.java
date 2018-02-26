package com.tektrill.learning.java8.threads.atomic;

/**
 * Created by @author isadsrj on 26/02/2018
 * To solve the consistency issue, we will have to make sure that increment operation on count is atomic,
 * we can do that using Synchronization but Java 5 java.util.concurrent.atomic provides wrapper classes for int and long
 * that can be used to achieve this atomic operation without usage of Synchronization.
 *
 * Here is the updated program that will always output count value as 8 because AtomicInteger method incrementAndGet() atomically increments the current value by one.
 */

import java.util.concurrent.atomic.AtomicInteger;

public class JavaAtomic {

	public static void main(String[] args) throws InterruptedException {

		ProcessingThreadWithAtomic pt = new ProcessingThreadWithAtomic();
		Thread t1 = new Thread(pt, "t1");
		t1.start();
		Thread t2 = new Thread(pt, "t2");
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Processing count=" + pt.getCount());
	}
}

class ProcessingThreadWithAtomic implements Runnable {
	private AtomicInteger count = new AtomicInteger();

	@Override
	public void run() {
		for (int i = 1; i < 5; i++) {
			processSomething(i);
			count.incrementAndGet();
		}
	}

	public int getCount() {
		return this.count.get();
	}

	private void processSomething(int i) {
		// processing some job
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}