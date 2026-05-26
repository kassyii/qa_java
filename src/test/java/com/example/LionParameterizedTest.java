package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LionParameterizedTest {

    @Mock
    Feline feline;

    @ParameterizedTest
    @MethodSource("provideLionConstructorReturnValid")
    void lionConstructorValid(String sex, boolean expected) throws Exception{
        Lion lion = new Lion(sex,feline);
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
    void lionConstructorReturnException(String sex,Feline feline) throws Exception{
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion(sex,feline);
        });
        assertTrue(exception.getMessage().contains("Используйте допустимые значения пола животного - самец или самка"));
    }

    private static Stream<Arguments> provideLionConstructorReturnException(){
        return Stream.of(
                Arguments.of("cамец",new Feline()),
                Arguments.of("cамка",new Feline()),
                Arguments.of("САмец",new Feline()),
                Arguments.of("САмка",new Feline()),
                Arguments.of("",new Feline()),
                Arguments.of("0",new Feline())
        );
    }

    @ParameterizedTest
    @MethodSource("provideHaveManeBoolean")
    void doesHaveManeBooleanParameterized(String sex, boolean expected) throws Exception {
        Lion lion = new Lion(sex,feline);

        boolean actual = lion.doesHaveMane();
        assertEquals(expected, actual,"Expected: " + expected + ", but actual: " + actual);
    }

    private static Stream<Arguments> provideHaveManeBoolean() {
        return Stream.of(
                Arguments.of( "Самец", true),
                Arguments.of("Самка", false)
        );
    }

}