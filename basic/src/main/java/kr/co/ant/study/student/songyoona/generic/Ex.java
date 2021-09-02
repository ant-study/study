/**
 * Author : yooS
 * Create Date : 2020. 9. 11.
 */
package kr.co.ant.study.student.songyoona.generic;

import lombok.extern.slf4j.Slf4j;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 11.
 */
@Slf4j
public class Ex {


    public static void main(String[] args) {
        // 1111111
//        String a = "nathan";
//        String b = "nathan";
//        String c = new String("nathan");
//
//        boolean t = false;
//        t = (a == b); // true
//        t = a == c; // false
//        t = a.equals(c); // true
//
//        c = a;
//
//        a = "vvv";
//        t = a == c; // false

        // xxxx
//        int a = 1;
//        int b = 2;
//        swap(a, b);
//
//        System.out.println(a); //출력결과 1
//        System.out.println(b); //출력결과 2
//
//        // oooo

        MyClass myClass1 = new MyClass(1);
        MyClass myClass2 = new MyClass(2);

        //두개의 필드 값을 바꿔 보자
        swapValue(myClass1, myClass2);
        System.out.println(myClass1.getIndex()); //출력결과 2
        System.out.println(myClass2.getIndex()); //출력결과 1


    }

    static void swap(int a, int b) {
        int tmp = a;// tmp=1
        a = b;// a=2
        b = tmp;
    }




    static void swapValue(MyClass m1, MyClass m2){
        int tmpIndex = m1.getIndex();
        m1.setIndex(m2.getIndex());
        m2.setIndex(tmpIndex);
    }

}
