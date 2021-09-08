# Study Project

* [Java Reflection](#java_reflection)
* [Generic ](#_generic)
* [OOP ](#_oop)
* [Java 8](#java8)
* [JPA](#_jpa)
* [Bean Validation](#validation) 
### 준비

 - 본인 Package 생성
    - kr.co.ant.study 패키지 뒤에 자기 영문이름으로 패키지명 생성
        - ex) kr.co.ant.study.hankwangsu
 - Study 주제
    - kr.co.ant.study.{공부할 패키지}
        - ex) kr.co.ant.study.reflect
---
### <a name="java_reflection" />Java Reflection
 
1. Object의 Field, Method 제어 (2020-09-02)
    - kr.co.ant.study.reflect.ReflectQuestion 클래스를 상속 받아서 아래 메소드 구현
        - getValue
        - setValue
        - copyProperties
    - 구현 클래스에서 main 메소드를 만들어서 testStart() 호출하여 결과 확인
        - ex) kr.co.ant.study.hankwangsu.Example 참고하여 개발
2. Anntation 생성 및 사용 그리고 Reflection을 활용한 제어
    - kr.co.ant.study.reflect.annotation.AnnotationQuestion 상속 받아서 아래 메소드 구현
        - validate(Object o)throws Exception; //입력 받은 LengthVO에 대해 유효성 검사
    - kr.co.ant.study.hankwangsu.AnnotationExample 참고
    - MaxLength, MinLength Annotation Class 구현
        - MaxLength(10) => 10자리 이상인 경우 오류
        - MinLength(5) => 5자리 이하인 경우 오류
3. 마지막 Reflection & Annotation => Spring @RequestMapping Annotaion 활용한 Web 요청 흉내내기
    - kr.co.ant.study.reflect.spring.SrpingCopy 클래스 상속 또는 자신 패키지로 복사하여 아래 메소드 구현, 상세 설명은 SrpingCopy 클래스내 주석 참고
    - void createController(Class clazz)
    - void initUrlMethod(Class clazz)
    - Object toParameter(Request request, Class parameterType)
    - Order Field에 Enum이 포함되어 있습니다.
---
    - 마지막이니 구글링해도 모르겠거나 궁금한건 저한테 바로 물어보세요
    - 언제까입니까? (휴가인데 숙제라니?)(송정우)
---
### <a name="_generic" />Generic

1. 기초
    - kr.co.ant.study.generic.Convertor 클래스를 자기 패키지로 복사해서 구현
    - toVO 메소드는 Map을 JavaBean으로 변환하는 메소드다
    - 현재 Convertor 클래스는 Generic을 1도 안써서 형변환이 덕지덕지 붙어있음 toVO, main 메소드를 Generic을 사용하여 형변환 없이 구현
---
### <a name="_oop" />OOP
1. 결제 처리 업무를 객체지향 적으로 만들어 보자
    - 기본 Package 경로 kr.co.ant.study.oop
    - Test 방법
        - Controller 생성후 해당 Controller 소스내 클래스 명 클릭후 Ctrl+1 눌러서 Junit Test Class 생성
        - 나머지는 이 소스 참고 : kr.co.ant.study.oop.controller.PaymentControllerTest
    - PG사 API Interface
        - 카드결제
        ```java
        {
            "product_id" : "P0001",
            "product_name" : "컴퓨터",
            "amount" : 4000000,
            "payment_type" : "CARD",
            "card_info" :{
                "card_no" : "2222444455556666",
                "card_code" : "003",
                "expire_date" : "092021"
            }
        }
        
         Response
        {
            "success" : true,
            "receipt" : {
                생각중
            }
        }
        ```
        - 휴대폰 결제
        ```java
        Request
        
        {
            "product_id" : "P0001",
            "product_name" : "컴퓨터",
            "amount" : 4000000,
            "payment_type" : "MOBILE",
            "mobile_info" :{
                "mobile_no" : "01011112222",
                "user_name" : "홍길동",
                "brithday" : "19900120"
            }
        }
        
         Response
        {
            "success" : true,
            "receipt" : {
               생각중 
            }
        }
        ```
        - 계좌이체
        ```java
        Request
        {
            "product_id" : "P0001",
            "product_name" : "컴퓨터",
            "amount" : 4000000,
            "payment_type" : "BANK",
            "bank_account_info" :{
                "account_no" : "22222111113333344444",
                "bank_code" : "102",
                "account_pw" : "1234"
            }
        }
        
        Response
        {
            "success" : true,
            "receipt" : {
                생각중
            }
        }
        ```
    - 1차목표
        1. Controller를 생성 하자
        2. 각 결제수단별로 Url을 달리 할지 하나의 Url로 처리할지 결정하자.
        3. PG사 API를 보고 업무클래스를 만들어 보자(VO)
          ex) CardInfo, MobileInfo....
    - 2차 목표 **(현재 참고만 하세요)**
        1. 결제 전 각 결제수단별 유효성 체크를 해야 한다.
            - 카드 : 카드번호 16자리
            - 휴대폰 : 10자리
            - 계좌번호 : 20자리
        2. 결제전 어느 결제수단이 얼마 결제 되었다는 로그를 남긴다.
        3. 결제후 응답 값의 결제성공 값은 결제수단별로 다를수도 같을 수도 있다.(이건 추후 정하겠음)
        4. 결제후 응답 포멧은 공통 부분이 있고 각 결제수단별로 다른 부분이 있다.(이건 추후 정하겠음)
    - OOP 세미나 자료
        - [oop.pptx](uploads/d850bc0cf50f6513b82f21d420773197/oop.pptx)        
---
### <a name="java8" />JAVA 8
1. Lamda
    - 간략 설명 자료 : kr.co.ant.study.java8.functional.Lamda.java 
    - 문제 : kr.co.ant.study.java8.functional.LamdaQuestion.java
    - 이제 어려운 문제
        - 대부분 Validator Interface를 만들어 놨죠?
        - Funtional 클래스를 사용하는 Validator를 구현한다음 기존 Validator를 교체 합시다.
        - FixedValidate, MinLengthValidate => functional 클래스를 사용한 Validate 하나로 처리 가능
            - hint
                - BIPredicate Function 사용
                - 기존 Validator는 Validator별로 로직이 안에 들어가있는데 함수형 Validator는 함수를 받아서 그 함수를 실행
        ```java
        예제)
        AnswerCardPayment cardPayment = new AnswerCardPayment(infoVO, new FixedLengthValidator());
         ==> AnswerCardPayment cardPayment = new AnswerCardPayment(infoVO, 요기다가 구현한 클래스 교체);
        ```
3. Funtional
    - java.util.function 패키지내 어떤 Funtion들이 있는지 참고 하세요
2. Stream
    - 설명 : kr.co.ant.study.java8.stream.BasicStream.java, StreamMapAndFlatMapAndReduce.java
    - 그외 따로 공부 요망
---
### <a name="_jpa" />JPA
*** PPT => [jpa1-150921075157-lva1-app6892.pdf](uploads/7d5d2cee96635411a36877e77615c57c/jpa1-150921075157-lva1-app6892.pdf)
1. 준비
    1. src/main/resources 폴더에 application-자기이니셜.yml 파일생성
        ```yml
        #자기 DB 접속정보 지정
        spring:
          datasource:
            url: jdbc:h2:tcp://localhost:9092/~/test
            username: 
            password: 
        ```
    2. Repository 소스 생성
        ```java
        @Repository
        public class MemberRepository {
        
        	@Autowired
        	private EntityManager em;
        	
        	
        	public void save(Member member) {
        		em.persist(member);
        	}
        }
        
        ```
    3. Test Code 생성
        ```java
        //동일한 Entity명이 있는경우 에러나기 때문에 본인의 package 경로로만 entity를 scan하게 설정 한다.
        @EntityScan(basePackages = "kr.co.ant.student.hankwangsu.jpa")
        
        @DataJpaTest //JPA 관련 설정 로딩
        @AutoConfigureTestDatabase(replace = Replace.NONE) //Test Database 사용안함
        @Import(MemberRepository.class) //Repository Annotation은 Jpa 관련 설정이 아니라 DataJpaTest시에 Bean으로 등록 되지 않기때문에 Import로 강제 등록
        @ActiveProfiles("hks") //자신이 설정한 application-이니셜.yml을 로드 하기 위해 Profile 설정
        @Rollback(false) //기본 Rollback으로 되어 있음 Rollback true이면 commit시 생성하는 쿼리가 실행되지 않기 때문에 공부할때는 귀찮아도 false로 해서 테스트
        
        class MemberRepositoryTest {
        
        	@Autowired
        	private MemberRepository repository;
        	
        	@Autowired
        	private EntityManager manager;
    	}
        ```
2. JDBC 기초와 EntityManager 설명
3. 테이블 맵핑관계를 Entity로 설정
4. JPQL
5. QeuryDSL
6. [jpa.pptx](uploads/89c7cc9db6563419ca57602401aff8b6/jpa.pptx)
7. [PPT 내 URL 링크 정리](https://git.ant-soft.co.kr/ant/study/wikis/JPA-%EC%B0%B8%EA%B3%A0-%EC%82%AC%EC%9D%B4%ED%8A%B8-%EB%AA%A9%EB%A1%9D)
---
### <a name="validation" />Bean Validation
[개인 정리 Validation Notion Link](https://amplified-approval-246.notion.site/Validate-bc01e254ddb04ea2bf38c086e96dbab0)
