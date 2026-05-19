package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    Felines felines;

    @ParameterizedTest
    @MethodSource("provideLionConstructorReturnValid")
    void LionConstructorValid(String sex, boolean expected) throws Exception{
        Lion lion = new Lion(sex);
        //конструктор из аргументов и сравниваю с тем что вернет метод hasMane
        assertEquals(expected,lion.hasMane,"Expected: " + expected + ", but actual: " + sex);
    }

    private static Stream<Arguments> provideLionConstructorReturnValid(){
        return Stream.of(
          Arguments.of("Самец", true),
          Arguments.of("Самка", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideLionConstructorReturnException")
    void LionConstructorReturnException(String sex) throws Exception{
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion(sex);
        });
        assertTrue(exception.getMessage().contains("Используйте допустимые значения пола животного - самец или самка"));
    }

    private static Stream<Arguments> provideLionConstructorReturnException(){
        return Stream.of(
                Arguments.of("cамец"),
                Arguments.of("cамка"),
                Arguments.of("САмец"),
                Arguments.of("САмка"),
                Arguments.of(""),
                //Arguments.of(null), метод не принимает null
                Arguments.of("0")
        );
    }

    @Test
    void getKittensSuccess() {
        Mockito.when(felines.getKittens()).thenReturn(1);
        Lion lion = new Lion(felines);
        int expected = 1;
        int actual = lion.getKittens();
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
        Mockito.verify(felines).getKittens();
    }

    //добавим параметризацию т.к. тест может принимать 2 булевых значния которые получаем из метода Lion
    @ParameterizedTest
    @MethodSource("provideHaveManeBoolean")
    void doesHaveManeBooleanParameterized(String sex, boolean expected) throws Exception {
        Lion lion = new Lion(sex);

        boolean actual = lion.doesHaveMane();
        assertEquals(expected, actual,"Expected: " + expected + ", but actual: " + actual);
    }

    private static Stream<Arguments> provideHaveManeBoolean() {
        return Stream.of(
                Arguments.of( "Самец", true),
                Arguments.of("Самка", false)
        );
    }


    @Test
    void getFoodPredatorSuccess() throws Exception{
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(felines.getFood("Хищник")).thenReturn(expected);
        Lion lion = new Lion(felines);
        List<String> actual = lion.getFood();
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
        Mockito.verify(felines).getFood("Хищник");
    }
}