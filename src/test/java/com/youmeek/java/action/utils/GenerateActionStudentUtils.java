package com.youmeek.java.action.utils;


import com.youmeek.java.action.pojo.Authority;
import com.youmeek.java.action.pojo.Student;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenerateActionStudentUtils {

	public static List<Student> list() throws ParseException {

		String date1String = "2014-04-17 17:07:37";
		String date2String = "2018-04-17 17:07:37";
		String date3String = "2014-04-17 19:07:37";
		String date4String = "2016-02-22 19:07:37";
		String date5String = "2017-09-21 10:17:37";

		DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime1 = LocalDateTime.parse(date1String, dateTimeFormatter2);
		LocalDateTime localDateTime2 = LocalDateTime.parse(date2String, dateTimeFormatter2);
		LocalDateTime localDateTime3 = LocalDateTime.parse(date3String, dateTimeFormatter2);
		LocalDateTime localDateTime4 = LocalDateTime.parse(date4String, dateTimeFormatter2);
		LocalDateTime localDateTime5 = LocalDateTime.parse(date5String, dateTimeFormatter2);


		Authority authority1 = new Authority();
		authority1.setId(1L);
		authority1.setName("top_admin");

		Authority authority2 = new Authority();
		authority2.setId(2L);
		authority2.setName("admin");
		
		Set<Authority> authoritySet1 = new HashSet<>();
		authoritySet1.add(authority1);
		authoritySet1.add(authority2);
		
		Set<Authority> authoritySet2 = new HashSet<>();
		authoritySet2.add(authority2);

		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(1, "YouMeek", 12, getEpochMilliByLocalDateTime(localDateTime1), authoritySet1));
		studentList.add(new Student(2, "Admin", 16, getEpochMilliByLocalDateTime(localDateTime2), authoritySet1));
		studentList.add(new Student(3, "GitNavi", 10, getEpochMilliByLocalDateTime(localDateTime3), authoritySet2));
		studentList.add(new Student(4, "SayShy", 22, getEpochMilliByLocalDateTime(localDateTime4), authoritySet1));
		studentList.add(new Student(5, "DuskLife", 36, getEpochMilliByLocalDateTime(localDateTime5), authoritySet2));

		return studentList;
	}

	public static Map<String, Student> map() throws ParseException {
		List<Student> studentList = list();
		Map<String, Student> studentMap = new HashMap<>();
		studentMap.put("A", studentList.get(0));
		studentMap.put("B", studentList.get(1));
		studentMap.put("C", studentList.get(2));
		studentMap.put("DA", studentList.get(3));
		studentMap.put("E", studentList.get(4));
		return studentMap;
	}

	private static long getEpochMilliByLocalDateTime(LocalDateTime localDateTime) {
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
}
