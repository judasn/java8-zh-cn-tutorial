package com.youmeek.java.sample;

import java.time.Instant;
import java.util.Date;

public class Hello {
	public static void main(String[] args) {

		int time = 1000;

		Date d1 = new Date();
		Thread1 thread1;
		for (int i = 0; i < time; i++) {
			thread1 = new Thread1();
			thread1.start();
		}
		Date d2 = new Date();
		System.out.println(d2.getTime() - d1.getTime());


		Date d3 = new Date();
		Thread2 thread2;
		for (int i = 0; i < time; i++) {
			thread2 = new Thread2();
			thread2.start();
		}
		Date d4 = new Date();
		System.out.println(d4.getTime() - d3.getTime());


		Date d5 = new Date();
		Thread3 thread3;
		for (int i = 0; i < time; i++) {
			thread3 = new Thread3();
			thread3.start();
		}
		Date d6 = new Date();
		System.out.println(d6.getTime() - d5.getTime());

	}
}

class Thread1 extends Thread {
	@Override
	public void run() {

		System.currentTimeMillis();

	}
}


class Thread2 extends Thread {
	@Override
	public void run() {

		new Date();

	}
}

class Thread3 extends Thread {
	@Override
	public void run() {
		Instant.now();
	}
}