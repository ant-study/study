package kr.co.ant.study.reflect;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ReflectQuestion {
	
	@Getter
	@Setter
	@ToString
	public static class Member {
		private String id;
		private String name;
		private int age;
		private LocalDate birthday;
	}
	
	@Getter
	@Setter
	@ToString
	@EqualsAndHashCode
	public static class MemberVO {
		private String id;
		private String name;
		private int age;
		private LocalDate birthday;
	}
	
	
	/**
	 * Test Sample Data
	 * @return
	 */
	public MemberVO sampleData() {
		MemberVO vo = new MemberVO();
		vo.setName("홍길동");
		vo.setAge(31);
		vo.setBirthday(LocalDate.of(1989, 3, 12));
		vo.setId("A0001");
		return vo;
	}
	

	/**
	 * Reflect을 사용해서 Object에 있는 Property 값 가져오기
	 * @param vo
	 * @param fieldName
	 * @return
	 */
	public abstract Object getValue(Object vo, String fieldName)throws Exception;
	
	/**
	 * Reflect을 사용해서 Object에 있는 Property 값 셋팅
	 * @param vo
	 * @param value
	 */
	public abstract void setValue(Object vo, Object value, String filedName)throws Exception;
	
	/**
	 * Reflect을 이용해서 MemberVO에 있는 Property값을 Member에 Property값 복사
	 * @param orig
	 * @param dest
	 * @throws Exception
	 */
	public abstract void copyProperties(Object orig, Object dest) throws Exception;
	
	public void startTest() throws Exception {
		log.info("Test Start");
	    /////////////// setValue Test ////////////////
		MemberVO orgi =  new MemberVO();
		
		MemberVO testData = sampleData();
		
		setValue(orgi, testData.getId(), "id");
		assertStringEquals(orgi.getId(), "A0001");
		
		setValue(orgi, testData.getName(), "name");
		assertStringEquals(orgi.getName(), testData.getName());
		
		setValue(orgi, testData.getAge(), "age");
		assertNumberEquals(orgi.getAge(), testData.getAge());
		
		setValue(orgi, testData.getBirthday(), "birthday");
		assertStringEquals(orgi.getBirthday().format(DateTimeFormatter.ofPattern("yyyyMMdd")), testData.getBirthday().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		
    	/////////////// getValue Test ////////////////
		
		String id = (String) getValue(orgi, "id");
		assertStringEquals(orgi.getId(), id);
		
		String name = (String) getValue(orgi, "name");
		assertStringEquals(orgi.getName(), name);
		
		int age = (int) getValue(orgi, "age");
		assertNumberEquals(orgi.getAge(), age);

		LocalDate brithday = (LocalDate) getValue(orgi, "birthday");
		assertStringEquals(orgi.getBirthday().format(DateTimeFormatter.ofPattern("yyyyMMd")), brithday.format(DateTimeFormatter.ofPattern("yyyyMMd")));
		
		
		/////////////// copyProperties Test ////////////////
		Member member = new Member();
		copyProperties(orgi, member);
		
		/**/
		log.info(member.toString());
		
		assertStringEquals(orgi.getId(), member.getId());
		
		assertStringEquals(orgi.getName(), member.getName());
		
		assertNumberEquals(orgi.getAge(), member.getAge());
		
		assertStringEquals(orgi.getBirthday().format(DateTimeFormatter.ofPattern("yyyyMMdd")), member.getBirthday().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
		log.info("Test Success");
	}
	
	public static void assertStringEquals(Object orgi, Object dest) {
		Assert.isTrue(orgi.equals(dest), String.format("%s.equals(%s)", orgi, dest));
	}
	
	public static void assertNumberEquals(Number orgi, Number dest) {
		Assert.isTrue(orgi.equals(dest), String.format("%s.equals(%s)", orgi, dest));
	}

	
}
