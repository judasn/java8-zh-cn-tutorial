package com.youmeek.java.sample.main;

import com.youmeek.java.sample.pojo.Student;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

/**
 * Java 8 Optional 类常用方法可以看这里：
 * http://www.importnew.com/6675.html
 */
public class OptionalTest {

	/**
	 * 创建 Optional 对象
	 */
	@Test
	public void test12() {

		// 创建一个没有值的Optional对象（比较少用）
		Optional<String> emptyOpt = Optional.empty();


		Student student = new Student(1, "YouMeek", 12, new Date());

		// 使用一个非空的值创建Optional对象（较常见）
		Optional<Student> notNullObject = Optional.of(student);

		// 使用一个可以为空的值创建Optional对象（常见）
		Optional<Student> nullAbleObject = Optional.ofNullable(student);

	}

	/**
	 * 提取 Optional 对象的值
	 */
	@Test
	public void test122() {
		Student student = new Student(1, "YouMeek", 12, new Date());
		Optional<Student> nullAbleObject = Optional.ofNullable(student);
		Optional<String> name = nullAbleObject.map(Student::getName);
		System.out.println("--------------------------------" + name.get());
	}

	/**
	 * 提取 Optional 对象的值（orElse）
	 * orElse 如果非空则取出值，如果为空则取默认值
	 */
	@Test
	public void test1322() {
		Student student = new Student(1, "YouMeek", 12, new Date());

		if (student.getAge() > 20) {
			student = null;
		}
		Optional<Student> nullAbleObject2 = Optional.ofNullable(student);
		Optional<String> name2 = nullAbleObject2.map(Student::getName);
		System.out.println("--------------------------------" + name2.orElse("这是默认值"));

		if (student.getAge() > 10) {
			student = null;
		}
		Optional<Student> nullAbleObject = Optional.ofNullable(student);
		Optional<String> name = nullAbleObject.map(Student::getName);
		System.out.println("--------------------------------" + name.orElse("这是默认值"));

	}

	/**
	 * 提取 Optional 对象的值（orElseGet）
	 * orElseGet()：与 orElse() 方法作用类似，区别在于生成默认值的方式不同。该方法接受一个 Supplier<? extends T> 函数式接口参数，用于生成默认值
	 */
	@Test
	public void test13221() {
		Student student = new Student(1, "YouMeek", 12, new Date());

		if (student.getAge() > 20) {
			student = null;
		}
		Optional<Student> nullAbleObject2 = Optional.ofNullable(student);
		Optional<String> name2 = nullAbleObject2.map(Student::getName);
		System.out.println("--------------------------------" + name2.orElseGet(() -> "这是默认值"));

		if (student.getAge() > 10) {
			student = null;
		}
		Optional<Student> nullAbleObject = Optional.ofNullable(student);
		Optional<String> name = nullAbleObject.map(Student::getName);
		System.out.println("--------------------------------" + name.orElseGet(() -> "这是默认值"));
		
		// 没有则调用方法去产生一个
		System.out.println("--------------------------------" + name.orElseGet(() -> this.testOrElseGet(1)));

	}

	/**
	 * 提取 Optional 对象的值（orElseGet）（private）
	 *
	 * @param n
	 * @return
	 */
	private String testOrElseGet(int n) {
		int result = n + 1;
		return String.valueOf(result);
	}


	/**
	 * 提取 Optional 对象的值（orElseThrow）
	 * 与前面介绍的 get() 方法类似，当值为 null 时调用这两个方法都会抛出
	 */
	@Test
	public void test132121() {
		Student student = new Student(1, "YouMeek", 12, new Date());

		if (student.getAge() > 20) {
			student = null;
		}
		Optional<Student> nullAbleObject = Optional.ofNullable(student);
		Optional<String> name = nullAbleObject.map(Student::getName);
		System.out.println("--------------------------------" + name.orElseThrow(() -> new RuntimeException("这是抛出异常信息")));

		if (student.getAge() > 10) {
			student = null;
		}
		Optional<Student> nullAbleObject2 = Optional.ofNullable(student);
		Optional<String> name2 = nullAbleObject2.map(Student::getName);
		System.out.println("--------------------------------" + name2.orElseThrow(() -> new RuntimeException("这是抛出异常信息")));
	}

	/**
	 * 使用 filter() 方法过滤
	 */
	@Test
	public void test1321121() {
		Student student = new Student(1, "YouMeek", 12, new Date());

		Optional<Student> nullAbleObject = Optional.ofNullable(student);
		Optional<Student> result = nullAbleObject.filter(temp -> temp.getAge() > 10);

		Optional<String> name = result.map(Student::getName);
		//尽量避免使用 get() 方法
		System.out.println("--------------------------------" + name.get());
		System.out.println("--------------------------------" + name.orElse("空的时候这是默认值"));

	}

	/**
	 * 使用 filter() 方法过滤（连着写）
	 */
	@Test
	public void test13231121() {
		Student student = new Student(1, "YouMeek", 2, new Date());

		// 连着写，如果这个对象年龄大于 10 则取出名字，不然就是取默认值
		String name = Optional.ofNullable(student)
				.filter(temp -> temp.getAge() > 10)
				.map(Student::getName).orElse("这是默认值");
		System.out.println("--------------------------------" + name);

	}

	/**
	 * 使用 filter() 方法过滤（连着写，并修改要返回的对象）
	 */
	@Test
	public void test132311121() {
		Student student = new Student(1, "YouMeek", 2, new Date());

		// 连着写，如果这个对象年龄大于 10 则取出名字，不然就是取默认值
		Student result = Optional.ofNullable(student)
				.filter(temp -> temp.getAge() > 10)
				.map(newStudent -> {
					newStudent.setId(11);
					newStudent.setName("新名字");
					return newStudent;
				}).orElse(new Student(1, "这是默认值", 2, new Date()));

		System.out.println("--------------------------------" + result.getName());

	}


}

