package com.github.Icear.NEFU.SimpleClass.Data.Class;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icear on 2017/10/30.
 * ClassUtilTest
 */
public class ClassUtilTest {
    @Test
    public void toStringTest() throws Exception {
        List<Integer> weeks = new ArrayList<>();
        weeks.add(1);
        weeks.add(2);
        weeks.add(3);
        weeks.add(5);
        weeks.add(7);
        weeks.add(8);
        weeks.add(10);
        weeks.add(11);
        weeks.add(12);
        weeks.add(13);
        weeks.add(14);

//        weeks.add(1);
//        weeks.add(3);
//        weeks.add(4);
//        weeks.add(5);
//        weeks.add(7);
//        weeks.add(8);
//        weeks.add(10);
//        weeks.add(11);
//        weeks.add(12);
//        weeks.add(14);


        System.out.println(ClassUtil.toString(weeks));
    }

}