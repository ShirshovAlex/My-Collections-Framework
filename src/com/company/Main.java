package com.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main<E extends Number>{

    E e;

    public <Y> Main(Y y){

    }

    public E sum(E e, E e1){
        return e;
    }

    public static Object abc(List<?> t){
        return null;
    }

    public static void main(String[] args) {
        Main<Integer> stringMain = new Main<>(22);
//        stringMain.sum(22, 22);

//        Main<Integer> integerMain = new Main<>();
//        integerMain.sum("sd", "asdasd");

        abc(new ArrayList<Object>());
//        Integer abc1 = abc(22);
    }
}
