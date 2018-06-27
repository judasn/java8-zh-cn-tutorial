package com.youmeek.java.sample.main;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;


public class CompletableFutureInAction3 {

	private final static Random RANDOM = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		//构建多个线程，如果是实际和 Spring 一起使用的时候，线程池是 Spring 维护，我们不用主动声明。
		ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
			Thread t = new Thread(r);
			t.setDaemon(false);
			return t;
		});

		//需要查询的商品 ID 列表
		List<Integer> productIdList = Arrays.asList(1, 2, 3, 4, 5);
		List<Double> result = productIdList
				.stream()
				.map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executor))
				.map(future -> future.thenApply(CompletableFutureInAction3::multiply))
				.map(CompletableFuture::join).collect(toList());

		System.out.println(result);
		executor.shutdown();
	}

	private static double multiply(double value) {
		try {
			//假设这里进行负责的计算处理流程
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return value * 10d;
	}

	private static double queryProduction(int productId) {
		return queryApi(productId);
	}

	private static double queryApi(int productId) {
		try {
			//假设根据商品 ID 查询数据库或者调用其他接口
			Thread.sleep(RANDOM.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double randomResult = RANDOM.nextDouble();
		System.out.println("--------------------------------randomResult=" + randomResult);
		return randomResult;
	}
}
