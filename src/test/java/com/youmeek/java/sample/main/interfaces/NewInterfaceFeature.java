package com.youmeek.java.sample.main.interfaces;

/**
 * 之前的接口只能有全局常量和抽象方法
 * JDK8 新增了 default 和 static 方法
 * 
 * 这个新特性一般目前实际用到的场景并不多，但是还是可以了解。
 */
public interface NewInterfaceFeature {

	/**
	 * 常规的抽象方法
	 */
	void oldMethod();

	/**
	 * 每个实现类都默认会有该方法，子类也可以选择重新该方法
	 */
	default void newDefaultMethod() {
		System.out.println("--------------------------------接口 default 修饰方法");
	}

	/**
	 * 只能使用接口类来直接调用
	 */
	static void newStaticMethod() {
		System.out.println("--------------------------------接口 static 修饰方法");
	}
}
