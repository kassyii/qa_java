package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    Feline feline;
    private final String sex = "Самец";

    @Test
    void getKittensSuccess() throws Exception {
        Mockito.when(feline.getKittens()).thenReturn(1);
        Lion lion = new Lion(sex,feline);
        int expected = 1;
        int actual = lion.getKittens();
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
        Mockito.verify(feline).getKittens();
    }


    @Test
    void getFoodPredatorSuccess() throws Exception{
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(expected);
        Lion lion = new Lion(sex,feline);
        List<String> actual = lion.getFood();
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
        Mockito.verify(feline).getFood("Хищник");
    }
}