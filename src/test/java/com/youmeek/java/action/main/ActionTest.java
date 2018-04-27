package com.youmeek.java.action.main;

import com.youmeek.java.action.pojo.Authority;
import com.youmeek.java.action.pojo.Student;
import com.youmeek.java.action.pojo.StudentDTO;
import com.youmeek.java.action.utils.GenerateActionStudentUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class ActionTest {


	//=====================================把 List<entity> 转换成 List<DTO> start=====================================

	@Test
	public void entityListToDTOList() throws ParseException {
		List<Student> studentList = GenerateActionStudentUtils.list();

		//排除掉 null
		List<StudentDTO> resultList = studentList.stream()
				.filter(Objects::nonNull)
				.map(this::entityToDTO)
				.collect(Collectors.toList());

		resultList.forEach(e -> System.out
				.println("Id:" + e.getMyId() + ", Name: " + e.getMyName() + ", Age:" + e.getMyAge() + ", CreateDatetime:" + e
						.getMyCreateDatetime() + ", Authorities: " + e.getAuthorities()));

	}

	private StudentDTO entityToDTO(Student student) {
		return new StudentDTO(student);
	}

	//=====================================把 List<entity> 转换成 List<DTO>  end=====================================


	//=====================================把 List<DTO> 转换成 List<entity> start=====================================

	@Test
	public void dtoListToEntityList() throws ParseException {

		List<Student> studentList = GenerateActionStudentUtils.list();

		//排除掉 null
		List<StudentDTO> studentDTOList = studentList.stream()
				.filter(Objects::nonNull)
				.map(this::entityToDTO)
				.collect(Collectors.toList());

		List<Student> resultList = studentDTOList.stream()
				.filter(Objects::nonNull)
				.map(this::dtoToEntity)
				.collect(Collectors.toList());

		resultList.forEach(e -> System.out
				.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e
						.getCreateDatetime() + ", Authorities: " + e.getAuthorities()));
	}

	private Student dtoToEntity(StudentDTO studentDTO) {
		if (studentDTO == null) {
			return null;
		} else {
			Student student = new Student();
			student.setId(studentDTO.getMyId());
			student.setName(studentDTO.getMyName());
			student.setAge(studentDTO.getMyAge());
			student.setCreateDatetime(studentDTO.getMyCreateDatetime());

			Set<Authority> authorities = this.authoritiesFromStrings(studentDTO.getAuthorities());
			if (authorities != null) {
				student.setAuthorities(authorities);
			}
			return student;
		}
	}

	private Set<Authority> authoritiesFromStrings(Set<String> strings) {
		return strings.stream().map(string -> {
			Authority auth = new Authority();
			auth.setName(string);
			return auth;
		}).collect(Collectors.toSet());
	}


	//=====================================把 List<DTO> 转换成 List<entity> end=====================================
	
/*

	//=====================================Service 层，根据 id 查询单个对象（带 entity 转 dto） start=====================================


	public Optional<StudentDTO> findById(Long id) {
		// 如果查询结果为空，则直接返回一个空白的对象
		return Optional.ofNullable(studentMapper.findOne(id)).map(this::entityToDTO);
	}

	//=====================================Service 层，根据 id 查询单个对象（带 entity 转 dto） end=====================================

	//=====================================Service 层，根据 id 查询单个对象，然后对该对象的某些属性做特殊处理 start=====================================


	public Optional<Student> findById2(Long id) {
		return Optional.ofNullable(studentMapper.findOne(id))
				.map(student -> {
					student.setId(1L);
					student.setName(studentMapper.findOne(1L));
					log.debug("Activated student: {}", student);
					return student;
				});
	}

	//=====================================Service 层，根据 id 查询单个对象，然后对该对象的某些属性做特殊处理 end=====================================

	//=====================================Service 层，根据 id 查询单个对象，并且判断年龄是否大于 20，大于才对该对象的某些属性做特殊处理 start=====================================


	public Optional<Student> findById2(Long id) {
		return Optional.ofNullable(studentMapper.findOne(id))
				.filter(student -> student.getAge() > 20)
				.map(student -> {
					student.setId(1L);
					student.setName(studentMapper.findOne(1L));
					log.debug("Activated student: {}", student);
					return student;
				});
	}

	//=====================================Service 层，根据 id 查询单个对象，并且判断年龄是否大于 20，大于才对该对象的某些属性做特殊处理 end=====================================

*/

}

