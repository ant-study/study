# Study Project

### 준비

 - 본인 Package 생성
    - kr.co.ant.study 패키지 뒤에 자기 영문이름으로 패키지명 생성
        - ex) kr.co.ant.study.hankwangsu
 - Study 주제
    - kr.co.ant.study.{공부할 패키지}
        - ex) kr.co.ant.study.reflect
---
### Java Reflection
 
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
### Generic

1. 기초
    - kr.co.ant.study.generic.Convertor 클래스를 자기 패키지로 복사해서 구현
    - toVO 메소드는 Map을 JavaBean으로 변환하는 메소드다
    - 현재 Convertor 클래스는 Generic을 1도 안써서 형변환이 덕지덕지 붙어있음 toVO, main 메소드를 Generic을 사용하여 형변환 없이 구현
---
### OOP
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
### JAVA 8
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
2. Stream
    - 설명 : kr.co.ant.study.java8.stream.BasicStream.java, StreamMapAndFlatMapAndReduce.java
    - 그외 따로 공부 요망
---
### JPA
1. JDBC 기초
    -작성중