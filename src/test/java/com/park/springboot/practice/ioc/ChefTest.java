package com.park.springboot.practice.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest

class ChefTest {
    @Autowired
    IngredientFactory ingredientFactory;
    @Autowired
    Chef chef;
    @Test
    void _돈까스_요리하기(){
        //준비
//         IngredientFactory ingredientFactory =new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu="돈까스";
        //수행
        String food =chef.cook(menu);
        //예상
        String expected="한돈 등심으로 만든 돈까스";
        //검증
        assertEquals(expected,food);
        System.out.println(food);
    }

    @Test
    void _스테이크_요리하기(){
        //준비
//        IngredientFactory ingredientFactory =new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu="스테이크";
        //수행
        String food =chef.cook(menu);
        //예상
        String expected="한우 꽃등심으로 만든 스테이크";
        //검증
        assertEquals(expected,food);
        System.out.println(food);
    }
    @Test
    void  _치킨_만들기(){
        //준비
//        IngredientFactory ingredientFactory =new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu="치킨";
        //수행
        String food =chef.cook(menu);
        //예상
        String expected="국내산 10호 닭으로 만든 치킨";
        //검증
        assertEquals(expected,food);
        System.out.println(food);
    }

}