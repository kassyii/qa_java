package com.example;

import java.util.List;

public class Feline extends Animal implements Predator,Felines {

    @Override
    public List<String> eatMeat() throws Exception {
        return getFood("Хищник");
    }

    @Override
    public String getFamily() {
        return "Кошачьи";
    }

    @Override
    public int getKittens() {
        return getKittens(1);
    }

    @Override
    public int getKittens(int kittensCount) {
        return kittensCount;
    }

    //Переопределил метод и сделал реализацию из Animal
    //Сделанно так из-за созданного интерфейса Felines , а т.к. изначально не было метода Feline.getFood() , то дал ему логику из наследуемого класса
    @Override
    public List<String> getFood(String felineKind) throws Exception {
        return  super.getFood(felineKind);
    }

}
