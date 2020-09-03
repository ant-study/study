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
        - 
2. Anntation 생성 및 사용 그리고 Reflection을 활용한 제어
    - kr.co.ant.study.reflect.annotation.AnnotationQuestion 상속 받아서 아래 메소드 구현
        - validate(Object o)throws Exception; //입력 받은 LengthVO에 대해 유효성 검사
    - MaxLength, MinLength Annotation Class 구현