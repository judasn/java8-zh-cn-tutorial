package com.youmeek.java.sample.main;

import com.youmeek.java.sample.pojo.Student;
import com.youmeek.java.sample.pojo.StudentDTO;
import com.youmeek.java.sample.utils.GenerateSampleStudentUtils;
import org.junit.Test;

import java.text.ParseException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream 流就像一个高级版本的 Iterator，而和迭代器又不同的是，Stream 可以并行化操作
 * <p>
 * 当我们使用一个流的时候，通常包括三个基本步骤：
 * 获取一个数据源（source）→ 数据转换→执行操作获取想要的结果，
 * 每次转换原有 Stream 对象不改变，返回一个新的 Stream 对象（可以有多次转换），
 * 这就允许对其操作可以像链条一样排列，变成一个管道。
 * <p>
 * stream有两种操作：Intermediate（懒操作）和 Terminal（最终操作，只能有一个）,还有特殊的 Short-circuiting 短路操作
 * 其中，转换操作都是 lazy 的，多个转换操作只会在 Terminal 操作的时候融合起来，一次循环完成
 */
public class StreamTest {


	//=====================================创建流 start=====================================

	/**
	 * 从 1 开始，遍历 20 个奇数
	 * <p>
	 * 下面通过 Stream.iterate(1, (i) -> i + 1) 创建一个源
	 * <p>
	 * filter 和 limit(20) 为 intermediate（懒操作），进行数据筛选和转换
	 * <p>
	 * collect()为 Terminal（最终操作，只能有一个），返回结果
	 *
	 * @throws ParseException
	 */
	@Test
	public void test1131() {
		List<Integer> list = Stream.iterate(1, (i) -> i + 1)
				.filter(i -> i % 2 != 0)
				.limit(20)
				.collect(Collectors.toList());
		System.out.println(list);
	}
	//=====================================创建流 end=====================================

	//=====================================Map 类型处理 start=====================================


	/**
	 * 找出名字中包含 e 的所有对象
	 *
	 * @throws ParseException
	 */
	@Test
	public void test231() throws ParseException {
		Map<String, Student> studentMap = GenerateSampleStudentUtils.map();

		// 写法一
		Map<String, Student> resultMap = studentMap.entrySet().stream()
				.filter(x -> (x.getValue().getName()).contains("e"))
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

		// 写法二
		Map<String, Student> resultMap2 = studentMap.entrySet().stream()
				.filter(x -> (x.getValue().getName()).contains("e"))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
		resultMap2.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}

	/**
	 * 找出名字中包含 e 的所有名字，用逗号隔开
	 *
	 * @throws ParseException
	 */
	@Test
	public void test2131() throws ParseException {
		Map<String, Student> studentMap = GenerateSampleStudentUtils.map();

		String result = studentMap.entrySet().stream()
				.filter(x -> (x.getValue().getName()).contains("e"))
				.map(x -> x.getValue().getName())
				.collect(Collectors.joining(","));

		System.out.println("--------------------------------" + result);
	}


	/**
	 * 找出名字中包含 e，并且年龄大于 20 的所有对象
	 *
	 * @throws ParseException
	 */
	@Test
	public void test23431() throws ParseException {
		Map<String, Student> studentMap = GenerateSampleStudentUtils.map();
		//方式一
		Map<String, Student> resultMap = studentMap.entrySet().stream()
				.filter(x -> {
					if (!x.getValue().getName().contains("e") && x.getValue().getAge() > 20) {
						return true;
					}
					return false;
				})
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

		System.out.println("======================================================================================");

		//方式二
		//直接使用forEach 作为Terminal操作（最终操作），输出结果
		studentMap.entrySet().stream()
				.filter(x -> {
					if (!x.getValue().getName().contains("e") && x.getValue().getAge() > 20) {
						return true;
					}
					return false;
				})
				.forEach(stringStudentEntry ->
						System.out.println("Key : " + stringStudentEntry.getKey() +
								" Value : " + stringStudentEntry.getValue()));
	}


	/**
	 * 找出名字中包含 e，并且年龄大于 20 的所有名字，用逗号隔开
	 *
	 * @throws ParseException
	 */
	@Test
	public void test23311() throws ParseException {
		Map<String, Student> studentMap = GenerateSampleStudentUtils.map();

		String result = studentMap.entrySet().stream()
				.filter(x -> {
					if (!x.getValue().getName().contains("e") && x.getValue().getAge() > 20) {
						return true;
					}
					return false;
				})
				.map(map -> map.getValue().getName())
				.collect(Collectors.joining(","));

		System.out.println("--------------------------------" + result);
	}


	/**
	 * 多个条件筛选（利用 Predicate）
	 *
	 * @throws ParseException
	 */
	@Test
	public void test2334431() throws ParseException {
		Map<String, Student> studentMap = GenerateSampleStudentUtils.map();

		Map<String, Student> resultMap = filterByValue(studentMap, x -> x.getName().contains("e"));
		Map<String, Student> resultMap2 = filterByValue(studentMap, x -> (x.getName().contains("e") && x
				.getAge() > 20));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
		System.out.println("--------------------------------");
		resultMap2.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}

	/**
	 * 多个条件筛选（利用 Predicate）（private）
	 *
	 * @throws ParseException
	 */
	private <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
		return map.entrySet()
				.stream()
				.filter(x -> predicate.test(x.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	/**
	 * 找出 key 包含 A 的 Map
	 *
	 * @throws ParseException
	 */
	@Test
	public void test2331() throws ParseException {
		Map<String, Student> studentMap = GenerateSampleStudentUtils.map();

		// 写法一
		Map<String, Student> resultMap = studentMap.entrySet().stream()
				.filter(x -> x.getKey().contains("A"))
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

		// 写法二
		Map<String, Student> resultMap2 = studentMap.entrySet().stream()
				.filter(x -> x.getKey().contains("A"))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
		System.out.println("--------------------------------");
		resultMap2.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

	}


	//=====================================Map 类型处理  end=====================================


	//=====================================List 类型处理 start=====================================

	/**
	 * 把所有字符串改为大写后输出
	 */
	@Test
	public void test1() {
		List<String> wordList = Arrays.asList("abc", "bcd", "cde");
		List<String> resultList = wordList.stream().map(String::toUpperCase).collect(Collectors.toList());
		resultList.forEach(System.out::println);
	}

	/**
	 * 把 list 中的数值都乘于 2
	 */
	@Test
	public void test10() {
		List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> resultList = num.stream().map(n -> n * 2).collect(Collectors.toList());
		resultList.forEach(System.out::println);
	}


	/**
	 * 根据 创建时间 正序
	 */
	@Test
	public void test11() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		List<Student> resultList = studentList.stream().sorted(Comparator.comparing(Student::getCreateDatetime))
				.collect(Collectors.toList());
		resultList.forEach(e -> System.out
				.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e
						.getCreateDatetime()));
	}

	/**
	 * 根据 创建时间 倒序
	 */
	@Test
	public void test12() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		List<Student> resultList = studentList.stream()
				.sorted(Comparator.comparing(Student::getCreateDatetime).reversed()).collect(Collectors.toList());
		resultList.forEach(e -> System.out
				.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e
						.getCreateDatetime()));
	}


	/**
	 * 找出所有学生名字（List of objects -> List of String）
	 */
	@Test
	public void test2() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		List<String> userNameList = studentList.stream().map(x -> x.getName()).collect(Collectors.toList());

		userNameList.forEach(System.out::println);
	}


	/**
	 * 找出年龄大于 20 的用户（List 对象）
	 */
	@Test
	public void test212() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		List<Student> resultList =
				studentList.stream()
						.filter(user -> user.getAge() > 20)
						.collect(Collectors.toList());

		resultList.forEach(e -> System.out
				.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e
						.getCreateDatetime()));
	}


	/**
	 * 找出年龄大于 20，并且用户名为 DuskLife 的用户（单个对象）
	 * findAny(); 查找任意元素
	 */
	@Test
	public void test2112() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		// 返回的是单个对象，不是 List
		Student student1 = studentList.stream()
				.filter((p) -> "DuskLife".equals(p.getName()) && 20 < p.getAge())
				.findAny()
				.orElse(null);

		System.out.println("student1 :" + student1);

		// 另外一种写法
		Student student2 = studentList.stream()
				.filter(p -> {
					if ("SayShy".equals(p.getName()) && 20 < p.getAge()) {
						return true;
					}
					return false;
				}).findAny()
				.orElse(null);

		System.out.println("student2 :" + student2);
	}

	/**
	 * 找出年龄大于 20 的用户名（List 对象）
	 */
	@Test
	public void test21221() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		List<String> userNameList =
				studentList.stream()
						.filter(user -> user.getAge() > 20)
						.map(Student::getName)
						.collect(Collectors.toList());

		userNameList.forEach(System.out::println);
	}

	/**
	 * 找出年龄大于 20 的用户名，并且用逗号隔开（List 对象）
	 */
	@Test
	public void test211221() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		String result = studentList.stream()
				.filter(user -> user.getAge() > 20)
				.map(Student::getName)
				.collect(Collectors.joining(","));

		System.out.println("--------------------------------" + result);
	}

	/**
	 * 找出年龄大于 20 的用户名（单个对象）
	 */
	@Test
	public void test2121() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		String name = studentList.stream()
				.filter(x -> "DuskLife".equals(x.getName()))
				.map(Student::getName)                        //convert stream to String
				.findAny()
				.orElse("");

		System.out.println("name : " + name);
	}


	/**
	 * 找出年龄大于 20 的用户名，并且根据用户创建时间排序
	 */
	@Test
	public void test22() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		List<String> userNameList =
				studentList.stream()
						.filter(user -> user.getAge() > 20)
						.sorted(Comparator.comparing(Student::getCreateDatetime))
						.map(Student::getName)
						.collect(Collectors.toList());

		userNameList.forEach(System.out::println);
	}

	/**
	 * 把 PO 转换成 DTO（List of objects -> List of other objects）
	 */
	@Test
	public void test222() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		List<StudentDTO> resultList = studentList.stream().map(temp -> {
			StudentDTO obj = new StudentDTO();
			obj.setMyId(temp.getId());
			obj.setMyName(temp.getName());
			obj.setMyAge(temp.getAge());
			obj.setMyCreateDatetime(temp.getCreateDatetime());
			return obj;
		}).collect(Collectors.toList());

		resultList.forEach(e -> System.out.println("Id:" + e.getMyId() + ", Name: " + e.getMyName() + ", Age:" + e
				.getMyAge() + ", CreateDatetime:" + e.getMyCreateDatetime()));

	}

	/**
	 * 计算所有年龄的总值
	 * reduce（归纳），一般和 map 一起搭配使用
	 *
	 * @throws ParseException
	 */
	@Test
	public void test3() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		Optional<Integer> totalAge =
				studentList.stream()
						.map(Student::getAge)
						.reduce((n, m) -> n + m);

		System.out.println("--------------------------------计算所有年龄的总值=" + totalAge.get());
	}

	/**
	 * 年龄最高的人
	 *
	 * @throws ParseException
	 */
	@Test
	public void test4() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		// 写法一
		Optional<Student> expensive = studentList.stream().max(Comparator.comparing(Student::getAge));

		// 写法二
		Optional<Student> expensive2 = studentList.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Student::getAge)));
		System.out.println("--------------------------------年龄最高的人=" + expensive.get().getName() + " , 年龄=" + expensive
				.get().getAge());
	}

	/**
	 * 年龄最低的人
	 *
	 * @throws ParseException
	 */
	@Test
	public void test5() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		// 写法一
		Optional<Student> cheapest = studentList.stream().min(Comparator.comparing(Student::getAge));

		// 写法二
		Optional<Student> cheapest2 = studentList.stream()
				.collect(Collectors.minBy(Comparator.comparing(Student::getAge)));
		System.out.println("--------------------------------年龄最低的人=" + cheapest.get().getName() + " , 年龄=" + cheapest
				.get().getAge());
	}

	/**
	 * Collectors 计算
	 *
	 * @throws ParseException
	 */
	@Test
	public void test6() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		LongSummaryStatistics longSummaryStatistics = studentList.stream()
				.collect(Collectors.summarizingLong(Student::getAge));
		long sum = longSummaryStatistics.getSum();// 年龄汇总
		double average = longSummaryStatistics.getAverage();// 求平均数
		long totalCount = longSummaryStatistics.getCount();// 计算总数
		long max = longSummaryStatistics.getMax();// 最大值
		long min = longSummaryStatistics.getMin();          // 最小值
		String result = String.format("年龄汇总=%s, 求平均数=%s, 计算总数=%s, 最大值=%s, 最小值=%s", sum, average, totalCount, max, min);
		System.out.println("--------------------------------最终结果：" + result);
	}

	/**
	 * 最小值，最大值
	 *
	 * @throws ParseException
	 */
	@Test
	public void test66() throws ParseException {
		int[] nums = {3, 2, 5, 1, 4};
		int minResult = IntStream.of(nums).min().getAsInt();
		// parallel() 启动并行计算方式
		int minResult2 = IntStream.of(nums).parallel().min().getAsInt();
		int maxResult = IntStream.of(nums).max().getAsInt();
		int sumResult = IntStream.of(nums).sum();
		long countResult = IntStream.of(nums).count();
		double averageResult = IntStream.of(nums).average().getAsDouble();
		int[] toArray = IntStream.of(nums).sorted().toArray();
		System.out.println("--------------------------------最小值：" + minResult);
		System.out.println("--------------------------------最小值2：" + minResult2);
		System.out.println("--------------------------------最大值：" + maxResult);
		System.out.println("--------------------------------计算和：" + sumResult);
		System.out.println("--------------------------------统计个数：" + countResult);
		System.out.println("--------------------------------平均数：" + averageResult);
		System.out.println("--------------------------------按小到大排序：" + toArray);
	}

	/**
	 * 根据用户名进行 group
	 *
	 * @throws ParseException
	 */
	@Test
	public void test6332() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();
		studentList.add(new Student(1, "YouMeek", 12, new Date()));

		Map<String, List<Student>> resultMap = studentList.stream().collect(
				Collectors.groupingBy(Student::getName)
		);

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}

	/**
	 * 根据用户名进行 group，并对同组进行计算个数
	 *
	 * @throws ParseException
	 */
	@Test
	public void test632() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();
		studentList.add(new Student(1, "YouMeek", 12, new Date()));

		Map<String, Long> resultMap = studentList.stream().collect(
				Collectors.groupingBy(
						Student::getName, Collectors.counting()
				)
		);

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}

	/**
	 * 根据用户名进行 group，并对同组的年龄进行求和
	 *
	 * @throws ParseException
	 */
	@Test
	public void test1632() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();
		studentList.add(new Student(1, "YouMeek", 12, new Date()));

		Map<String, Integer> resultMap = studentList.stream().collect(
				Collectors.groupingBy(Student::getName, Collectors.summingInt(Student::getAge)));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}

	/**
	 * 根据用户名进行 group，并对同组的年龄进行求平均
	 *
	 * @throws ParseException
	 */
	@Test
	public void test16332() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();
		studentList.add(new Student(1, "YouMeek", 112, new Date()));

		Map<String, Double> resultMap = studentList.stream().collect(
				Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getAge)));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}

	/**
	 * 根据用户名进行 group，并找出相同用户名下的不同年龄
	 *
	 * @throws ParseException
	 */
	@Test
	public void test163212() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();
		studentList.add(new Student(1, "YouMeek", 112, new Date()));

		Map<Integer, Set<Integer>> resultMap = studentList.stream().collect(
				Collectors.groupingBy(Student::getId,
						Collectors.mapping(Student::getAge, Collectors.toSet())
				)
		);

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}

	/**
	 * 根据年龄进行分区，根据年龄分出大于 20 和小于 20 两组
	 * key = true 表示 大于 20 的一组
	 * key = false 表示 小于 20 的一组
	 *
	 * @throws ParseException
	 */
	@Test
	public void test1632122() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();

		Map<Boolean, List<Student>> resultMap = studentList.stream()
				.collect(Collectors.partitioningBy(e -> e.getAge() > 20));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}

	/**
	 * 统计每个分区中的相同人名的个数
	 *
	 * @throws ParseException
	 */
	@Test
	public void test16321222() throws ParseException {
		List<Student> studentList = GenerateSampleStudentUtils.list();
		studentList.add(new Student(1, "YouMeek", 13, new Date()));

		Map<Boolean, Map<String, Long>> resultMap = studentList.stream()
				.collect(Collectors.partitioningBy(e -> e.getAge() > 20,
						Collectors.groupingBy(Student::getName, Collectors.counting())));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}


	/**
	 * 通过 parallelStream() 方法可以简单地创建一个并发的流
	 * <p>
	 * 例子：
	 * 源是一个有20000*100*10个整数的列表
	 * <p>
	 * 一个并发，一个串行，它们都进行一下操作
	 * 从 1 开始，遍历 20000*100*10 个奇数
	 * <p>
	 * 结果：
	 * 把列表的大小调低时，串行效果更好
	 * 把列表的大小调高时，并发效果更好
	 *
	 * @throws ParseException
	 */
	@Test
	public void testParallel() {
		//创建一个有20000*100*10个整数的列表
		List<Integer> list = Stream.iterate(1, (i) -> i + 1)
				.limit(20000 * 100 * 10)
				.collect(Collectors.toList());

		long paralStart = Instant.now().toEpochMilli();
		list.parallelStream()
				.filter(i -> i % 2 != 0)
				.collect(Collectors.toList());
		long paralEnd = Instant.now().toEpochMilli();
//        System.out.println(list);
		long pt = paralEnd - paralStart;
		System.out.println("并发执行时间：" + pt);

		long start = Instant.now().toEpochMilli();
		list.stream()
				.filter(i -> i % 2 != 0)
				.collect(Collectors.toList());
		long end = Instant.now().toEpochMilli();
		long t = end - start;
		System.out.println("直接执行时间：" + t);
	}
	//=====================================List 类型处理  end=====================================


}

