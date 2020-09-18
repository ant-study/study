package kr.co.ant.study.student.seomyeongjoo.oop.util;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Validate {

    public String name;
    public int size;

    public abstract boolean validate();
}
