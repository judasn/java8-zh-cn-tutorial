package com.youmeek.java.sample.main;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * 旧的 API 中 java.util.Date 和 java.util.Calendar 线程不安全，通用性差，以后基本都可以换掉了
 */
public class DateTimeTest {


	/**
	 * LocalDate 类表示一个具体的日期（yyyy-MM-dd），但不包含具体时间，也不包含时区信息
	 */
	@Test
	public void testLocalDate() {
		//
		LocalDate localDateObject = LocalDate.of(2017, 1, 4);     // 初始化一个日期：2017-01-04
		int year = localDateObject.getYear();                     // 年份：2017
		Month month = localDateObject.getMonth();                 // 第几月-英文：JANUARY
		int monthValue = localDateObject.getMonthValue();        // 第几份：1
		int dayOfYear = localDateObject.getDayOfYear();        // 这一年的第几天
		int dayOfMonth = localDateObject.getDayOfMonth();         // 这个月份中的第几天：4
		int dayOfMonth2 = localDateObject.get(ChronoField.DAY_OF_MONTH);         //另外一种通用用法
		DayOfWeek dayOfWeek = localDateObject.getDayOfWeek();     // 一周的第几天：WEDNESDAY
		int lengthOfYear = localDateObject.lengthOfYear();             // 这一年一共有多少天：365
		int lengthOfMonth = localDateObject.lengthOfMonth();             // 月份的天数：31
		boolean leapYear = localDateObject.isLeapYear();          // 是否为闰年：false
		long toEpochDay = localDateObject.toEpochDay();          // 距离 1970-01-01 00:00:00 年过去了多少天
		System.out.println("--------------------------------year=" + year);
		System.out.println("--------------------------------month=" + month);
		System.out.println("--------------------------------dayOfYear=" + dayOfYear);
		System.out.println("--------------------------------monthValue（from 1 to 12）=" + monthValue);
		System.out.println("--------------------------------dayOfMonth=" + dayOfMonth);
		System.out.println("--------------------------------dayOfMonth2=" + dayOfMonth2);
		System.out.println("--------------------------------dayOfWeek.toString()=" + dayOfWeek.toString());
		System.out.println("--------------------------------dayOfWeek.getValue()<from 1 (Monday) to 7 (Sunday)>=" + dayOfWeek.getValue());
		System.out.println("--------------------------------lengthOfYear=" + lengthOfYear);
		System.out.println("--------------------------------lengthOfMonth=" + lengthOfMonth);
		System.out.println("--------------------------------leapYear=" + leapYear);
		System.out.println("--------------------------------toEpochDay=" + toEpochDay);
	}

	/**
	 * LocalDate 类表示一个具体的日期（yyyy-MM-dd），但不包含具体时间，也不包含时区信息
	 */
	@Test
	public void testLocalDate2() {
		LocalDate localDateObject = LocalDate.now();//获取当前日期
		int year = localDateObject.getYear();
		Month month = localDateObject.getMonth();
		int monthValue = localDateObject.getMonthValue();
		int dayOfMonth = localDateObject.getDayOfMonth();
		DayOfWeek dayOfWeek = localDateObject.getDayOfWeek();
		int lengthOfYear = localDateObject.lengthOfYear();
		int lengthOfMonth = localDateObject.lengthOfMonth();
		boolean leapYear = localDateObject.isLeapYear();
		long toEpochDay = localDateObject.toEpochDay();
		System.out.println("--------------------------------year=" + year);
		System.out.println("--------------------------------month=" + month);
		System.out.println("--------------------------------monthValue（from 1 to 12）=" + monthValue);
		System.out.println("--------------------------------dayOfMonth=" + dayOfMonth);
		System.out.println("--------------------------------dayOfWeek.toString()=" + dayOfWeek.toString());
		System.out.println("--------------------------------dayOfWeek.getValue()<from 1 (Monday) to 7 (Sunday)>=" + dayOfWeek.getValue());
		System.out.println("--------------------------------lengthOfYear=" + lengthOfYear);
		System.out.println("--------------------------------lengthOfMonth=" + lengthOfMonth);
		System.out.println("--------------------------------leapYear=" + leapYear);
		System.out.println("--------------------------------toEpochDay=" + toEpochDay);
	}

	/**
	 * LocalTime 只包含具体时间（HH:mm:ss）
	 */
	@Test
	public void testlocalTime() {
		LocalTime localTime = LocalTime.of(17, 23, 52); // 初始化一个时间：17:23:52
		int hour = localTime.getHour();
		int minute = localTime.getMinute();
		int second = localTime.getSecond();
		System.out.println("--------------------------------hour（from 0 to 23）=" + hour);
		System.out.println("--------------------------------minute（from 0 to 59）=" + minute);
		System.out.println("--------------------------------second（from 0 to 59）=" + second);
	}

	/**
	 * LocalDateTime 是 LocalDate 和 LocalTime 的结合体（yyyy-MM-dd HH:mm:ss）
	 * localDate 和 LocalTime 常用的方法它都有
	 */
	@Test
	public void testLocalDateTime() {
		// 直接声明
		LocalDateTime localDateTimeObject = LocalDateTime.of(2017, Month.JANUARY, 4, 17, 23, 52);

		// LocalDate + LocalTime 结合声明
		LocalDate localDate = LocalDate.of(2017, Month.JANUARY, 4);
		LocalTime localTime = LocalTime.of(17, 23, 52);
		LocalDateTime localDateTimeObject2 = localDate.atTime(localTime);
		LocalDateTime localDateTimeObject3 = LocalDateTime.of(localDate, localTime);

		System.out.println("--------------------------------localDateTimeObject2=" + localDateTimeObject2);
		System.out.println("--------------------------------localDateTimeObject3=" + localDateTimeObject3);

		// LocalDateTime 也可以转换成 LocalDate + LocalTime
		LocalDate date = localDateTimeObject.toLocalDate();
		LocalTime time = localDateTimeObject.toLocalTime();

	}

	/**
	 * Instant 用于表示一个时间戳，它与我们常使用的 System.currentTimeMillis()有些类似，不过 Instant 可以精确到纳秒（Nano-Second），System.currentTimeMillis() 方法只精确到毫秒（Milli-Second）
	 * <p>
	 * `Instant.now()` 会从系统默认的系统时钟 `clock` 获得 `Instant` 对象
	 */
	@Test
	public void testInstant() {
		Instant now = Instant.now();
		long currentTimeMillis = System.currentTimeMillis();
		long epochSecond = now.getEpochSecond();
		long toEpochMilli = now.toEpochMilli();

		System.out.println("--------------------------------currentTimeMillis=" + currentTimeMillis);
		System.out.println("--------------------------------epochSecond=" + epochSecond);// 1524581378 = 从 1970-01-01 00:00:00 开始到现在过的秒数
		System.out.println("--------------------------------toEpochMilli=" + toEpochMilli);// 1524581378418 = 从 1970-01-01 00:00:00 开始到现在过的毫秒数
	}

	/**
	 * 通过时间戳生成时间
	 */
	@Test
	public void testInstantCreate1() {
		Instant now1 = Instant.ofEpochSecond(1524581378L);
		Instant now2 = Instant.ofEpochMilli(1524581378418L);

		System.out.println("--------------------------------now1=" + now1);//2018-04-24T14:49:38Z
		System.out.println("--------------------------------now2=" + now2);//2018-04-24T14:49:38.418Z
	}

	/**
	 * 通过字符串生成 `Instant`
	 */
	@Test
	public void testInstantCreate2() {
		Instant instant1 = Instant.parse("2007-12-03T10:15:30.00Z");
		System.out.println("1524581378 = 从 1970-01-01 00:00:00 开始到现在过的秒数: " + instant1.getEpochSecond());
	}


	/**
	 * Duration 的内部实现与 Instant 类似，也是包含两部分：seconds 表示秒，nanos 表示纳秒。两者的区别是 Instant 用于表示一个时间戳（或者说是一个时间点），而 Duration 表示一个时间段，所以 Duration 类中不包含 now() 静态方法
	 */
	@Test
	public void testDuration() {
		LocalDateTime from = LocalDateTime.of(2017, Month.JANUARY, 5, 10, 7, 0);    // 2017-01-05 10:07:00
		LocalDateTime to = LocalDateTime.of(2017, Month.FEBRUARY, 5, 10, 7, 0);     // 2017-02-05 10:07:00
		Duration duration = Duration.between(from, to);     // 表示从 2017-01-05 10:07:00 到 2017-02-05 10:07:00 这段时间

		long days = duration.toDays();              // 这段时间的总天数
		long hours = duration.toHours();            // 这段时间的小时数
		long minutes = duration.toMinutes();        // 这段时间的分钟数
		long seconds = duration.getSeconds();       // 这段时间的秒数
		long milliSeconds = duration.toMillis();    // 这段时间的毫秒数
		long nanoSeconds = duration.toNanos();      // 这段时间的纳秒数

		System.out.println("--------------------------------days=" + days);
		System.out.println("--------------------------------hours=" + hours);
		System.out.println("--------------------------------minutes=" + minutes);
		System.out.println("--------------------------------seconds=" + seconds);
		System.out.println("--------------------------------milliSeconds=" + milliSeconds);
		System.out.println("--------------------------------nanoSeconds=" + nanoSeconds);
	}

	/**
	 * 两个时间的差，单纯的 年相减、月相减、日相减
	 */
	@Test
	public void testPeriod() {
		LocalDate localDate1 = LocalDate.of(1017, 1, 10);
		LocalDate localDate2 = LocalDate.of(2018, 4, 20);
		Period period = Period.between(localDate1, localDate2);

		//两个时间差：1001 年 3 个月 10 天
		System.out.println("--------------------------------period.getYears()=" + period.getYears());
		System.out.println("--------------------------------period.getMonths()=" + period.getMonths());
		System.out.println("--------------------------------period.getDays()=" + period.getDays());
	}


	/**
	 * TemporalAdjusters 时间调整器,它用于由当前日期时间经过调整后创建得到新的日期时间对象
	 * <p>
	 * 使用方式（官方推荐第二种）：
	 * 1是调用TemporalAdjuster对象的adjustInto(temporal)方法。
	 * 2使用Temporal.with(TemporalAdjuster)。
	 * 其中Temporal是一个接口，LocalDateTime和LocalDate等都实现了它,所以他们，它们具有有时间调整功能
	 * <p>
	 * 下面的例子是用LocalDate做时间调整
	 */
	@Test
	public void testTemporalAdjuster() {
		LocalDate localDateObject = LocalDate.now();
		LocalDate date1 = localDateObject.with(TemporalAdjusters
				.nextOrSame(DayOfWeek.SUNDAY));      // 返回下一个距离当前时间最近的星期日（如果今天就是星期天，则返回的是当天）（要跟 next 区分开）
		LocalDate date2 = localDateObject.with(TemporalAdjusters.lastInMonth(DayOfWeek.SATURDAY));   // 返回本月最后一个星期六
		LocalDate date3 = localDateObject.with(TemporalAdjusters.lastDayOfMonth());   // 返回本月最后一天日期
		LocalDate date4 = localDateObject.with(TemporalAdjusters.firstInMonth(DayOfWeek.SATURDAY));   // 返回本月最初一个星期六
		LocalDate date5 = localDateObject.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));   // 返回下一个的星期日
		LocalDate date6 = localDateObject.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));   // 返回上一个的星期日
		LocalDate date7 = localDateObject.with(TemporalAdjusters
				.previousOrSame(DayOfWeek.SUNDAY));   // 返回上一一个距离当前时间最近的星期日（如果今天就是星期天，则返回的是当天）（要跟 previous 区分开）

		System.out.println("--------------------------------date1=" + date1);
		System.out.println("--------------------------------date2=" + date2);
		System.out.println("--------------------------------date3=" + date3);
		System.out.println("--------------------------------date4=" + date4);
		System.out.println("--------------------------------date5=" + date5);
		System.out.println("--------------------------------date6=" + date6);
		System.out.println("--------------------------------date7=" + date7);


	}

	/**
	 * 自定义时间调整器
	 * <p>
	 * 计算下一个工作日的日期（排除周六日）
	 * 如果上面表格中列出的方法不能满足你的需求，你还可以创建自定义的 TemporalAdjuster 接口的实现，TemporalAdjuster 也是一个函数式接口
	 */
	@Test
	public void testCustomTemporalAdjuster() {
		LocalDate localDateObject = LocalDate.now();
		LocalDate date1 = localDateObject.with(temporal -> {
			// 当前日期
			DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

			// 正常情况下，每次增加一天
			int dayToAdd = 1;

			// 如果是星期五，增加三天
			if (dayOfWeek == DayOfWeek.FRIDAY) {
				dayToAdd = 3;
			}

			// 如果是星期六，增加两天
			if (dayOfWeek == DayOfWeek.SATURDAY) {
				dayToAdd = 2;
			}

			return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		});

		System.out.println("--------------------------------date1=" + date1);
	}


	/**
	 * 格式化日期（LocalDate 到 String ; String 到 LocalDate）
	 */
	@Test
	public void testDateFormat() {
		LocalDate localDate = LocalDate.now();
		String format1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);//结果格式是这样的：20180606
		String format2 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);//结果格式是这样的：2018-06-06
		System.out.println("--------------------------------format1=" + format1);
		System.out.println("--------------------------------format2=" + format2);


		DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String format3 = localDate.format(mySelfFormatter);
		System.out.println("--------------------------------format3=" + format3);
	}

	/**
	 * 格式化日期（LocalDateTime 到 String ; String 到 LocalDateTime）
	 */
	@Test
	public void testDateTimeFormatter() {
		LocalDateTime dateTime = LocalDateTime.now();
		String strDate1 = dateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
		String strDate2 = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
		String strDate3 = dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
		String strDate4 = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		String strDate5 = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String strDate6 = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA));


		System.out.println("--------------------------------strDate1=" + strDate1);
		System.out.println("--------------------------------strDate2=" + strDate2);
		System.out.println("--------------------------------strDate3=" + strDate3);
		System.out.println("--------------------------------strDate4=" + strDate4);
		System.out.println("--------------------------------strDate5=" + strDate5);
		System.out.println("--------------------------------strDate6=" + strDate6);
		System.out.println("======================================================");

		String strDate = "2018-04-24";
		LocalDate dateResult = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dateResult2 = LocalDate.parse(strDate,  DateTimeFormatter.ISO_LOCAL_DATE);
		
		System.out.println("--------------------------------dateResult=" + dateResult);
		System.out.println("--------------------------------dateResult2=" + dateResult2);
		
		System.out.println("======================================================");
		

		String strDate22 = "20180424";
		LocalDate dateResult3 = LocalDate.parse(strDate22,  DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("--------------------------------dateResult3=" + dateResult3);

		System.out.println("======================================================");

		String strDateTime = "2018-04-24 23:24:04";
		LocalDateTime dateTimeResult = LocalDateTime.parse(strDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		System.out.println("--------------------------------dateTimeResult=" + dateTimeResult);
	}

	/**
	 * LocalDate,LocalTime,LocalDateTime的时间大小比较通常用类自带的 isBefore() , isEqual()等方法，
	 * ChronoUnit 可用于计算出它们相差的时间大小
	 */
	@Test
	public void testTimeCompare() {
		String strTime1 = "2018-04-26 23:24:04";
		LocalDateTime time1 = LocalDateTime.parse(strTime1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		String strTime2 = "2018-04-24 23:24:04";
		LocalDateTime time2 = LocalDateTime.parse(strTime2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		System.out.println(time1.isBefore(time2));  // false  时间1是否在时间2之前
		System.out.println(time1.isAfter(time2));  // true
		System.out.println(time1.isEqual(time2));  // true

		long hoursBetween = ChronoUnit.HOURS.between(time1, time2);
		long minutesBetween = ChronoUnit.MINUTES.between(time1, time2);

		System.out.println(hoursBetween);       // -48 时间1和时间2相差的小时为48小时
		System.out.println(minutesBetween);     // -2880
	}

	/**
	 * LocalDateTime 和 String 互相转换
	 */
	@Test
	public void test22222() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDateTimeString = dateTimeFormatter.format(localDateTime);
		System.out.println("LocalDateTime 转成 String 类型的时间：" + localDateTimeString);

		//=================================================================================

		String strTime = "2018-04-26 23:24:04";
		DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime1 = LocalDateTime.parse(strTime, dateTimeFormatter2);
		System.out.println("String 类型的时间转成 LocalDateTime：" + localDateTime1);
	}

	/**
	 * LocalDateTime 转换成 unix timestamp
	 */
	@Test
	public void test221222() {
		ZoneId zoneId = ZoneId.systemDefault();
		//ZoneId zoneId = ZoneId.of("Europe/Oslo");
		//ZoneId zoneId = ZoneId.of("Asia/Shanghai");
		LocalDateTime localDateTime = LocalDateTime.now();
		long epochMilliByLocalDateTime = localDateTime.atZone(zoneId).toInstant().toEpochMilli();
		System.out.println("--------------------------------epochMilliByLocalDateTime=" + epochMilliByLocalDateTime);

		long epochMilliByInstant = Instant.now().toEpochMilli();
		System.out.println("--------------------------------epochMilliByInstant=" + epochMilliByInstant);

		long currentTimeMillis = System.currentTimeMillis();
		System.out.println("--------------------------------currentTimeMillis=" + currentTimeMillis);
	}

}

