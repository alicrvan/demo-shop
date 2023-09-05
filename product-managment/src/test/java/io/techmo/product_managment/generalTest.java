package io.techmo.product_managment;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import java.util.UUID;


public class generalTest {



    @Test
    void ideaTest(){

        String uuid = RandomString.make(12).toUpperCase();

        System.out.println(uuid);

        String name = "adam";
        int age = 23;
        String both = name + age;
        System.out.println(both.hashCode());


        String randomPart = RandomString.hashOf(both);
        System.out.println(randomPart);
    }
}
