package com.park.springboot.practice.ioc;

import org.springframework.stereotype.Component;

@Component
public class IngredientFactory {
    public Ingredient get(String menu) {
        switch (menu){
            case "돈까스" : return new Pork("한돈 등심");
            case "스테이크": return new Beef("한우 꽃등심");
            case "치킨": return new Chicken("국내산 10호 닭");
            default: return null;
        }
    }
}
