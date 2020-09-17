package kr.co.ant.study.seomyeongjoo.oop.util;

import kr.co.ant.study.seomyeongjoo.oop.domain.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Validate {

    public String name;
    public int size;

    public abstract boolean validate();
}
