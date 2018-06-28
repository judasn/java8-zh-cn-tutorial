package com.youmeek.java.sample.main;

import org.junit.Test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * lambda 表达式（匿名函数）在简单使用上就是用编写函数的方式编写匿名内部类代码
 * 所以学习 lambda 一定要多写写匿名内部类，写多了会觉得那个结构很吐血，才能体会 lambda
 */
public class AnonymousInnerClassTest {

	//=====================================实现 Runnable start=====================================

	@Test
	public void test1() {
		new Runnable() {
			@Override
			public void run() {
				System.out.println("--------------------------------匿名内部类实现 Runnable 接口");
			}
		}.run();
	}

	/**
	 * 写法 1
	 */
	@Test
	public void test11() {
		Runnable r = () -> {
			System.out.println("--------------------------------");
		};
		r.run();
	}

	/**
	 * 写法 2
	 */
	@Test
	public void test111() {
		((Runnable) () -> System.out.println("--------------------------------匿名内部类实现 Runnable 接口")).run();
	}

	//=====================================实现 Runnable  end=====================================

	//=====================================Collections sort start=====================================

	@Test
	public void test2() {
		List<String> list = Arrays.asList("a", "b", "c");
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Collator.getInstance().compare(o1, o2);
			}
		});
	}

	/**
	 * 写法 1
	 */
	@Test
	public void test22() {

		List<String> list = Arrays.asList("a", "b", "c");
		Collections.sort(list, (String o1, String o2) -> {
			return Collator.getInstance().compare(o1, o2);
		});
	}

	/**
	 * 写法 2
	 */
	@Test
	public void test222() {
		List<String> list = Arrays.asList("a", "b", "c");
		Collections.sort(list, (String o1, String o2) -> Collator.getInstance().compare(o1, o2));
	}

	/**
	 * 写法 3
	 */
	@Test
	public void test2222() {
		List<String> list = Arrays.asList("ac", "ab", "c");
		Collections.sort(list, (o1, o2) -> Collator.getInstance().compare(o1, o2));
		System.out.println("--------------------------------" + list);
	}

	/**
	 * 写法 4
	 * 这里引入一种新的 lambda 特殊语法：方法应用写法，用两个冒号组成，常见的有这几种情况：
	 * 类 :: 静态方法
	 * 类 :: new（就是构造方法）
	 * 对象 :: 方法
	 * 对象 :: 静态方法
	 */
	@Test
	public void test22222() {
		List<String> list = Arrays.asList("ac", "ab", "c");
		Collections.sort(list, Collator.getInstance() :: compare);
		System.out.println("--------------------------------" + list);
	}

	//=====================================Collections sort  end=====================================

}

