package com.example;

import java.util.List;

public interface Felines {

    int getKittens();

    int getKittens(int kittensCount);

    List<String> getFood(String felineKind) throws Exception;

}
