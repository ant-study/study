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
    
