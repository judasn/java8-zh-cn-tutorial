package com.youmeek.java.sample.utils;

import com.youmeek.java.sample.pojo.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateSampleStudentUtils {

	public static List<Student> list() throws ParseException {

		String date1String = "2014-04-17 17:07:37";
		String date2String = "2018-04-17 17:07:37";
		String date3String = "2014-04-17 19:07:37";
		String date4String = "2016-02-22 19:07:37";
		String date5String = "2017-09-21 10:17:37";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date1 = simpleDateFormat.parse(date1String);
		Date date2 = simpleDateFormat.parse(date2String);
		Date date3 = simpleDateFormat.parse(date3String);
		Date date4 = simpleDateFormat.parse(date4String);
		Date date5 = simpleDateFormat.parse(date5String);


		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(1, "YouMeek", 12, date1));
		studentList.add(new Student(2, "Admin", 16, date2));
		studentList.add(new Student(3, "GitNavi", 10, date3));
		studentList.add(new Student(4, "SayShy", 22, date4));
		studentList.add(new Student(5, "DuskLife", 36, date5));

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
}
