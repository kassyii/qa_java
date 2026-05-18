package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class FelineTest {


    //eatMeat передадим "Хищник" . Exception можно не проверять т.к. всегда передаем "Хищник"
    @Test
    void eatMeatReturnsPredator() throws Exception {
        Feline feline = new Feline();
        List<String> actual = feline.eatMeat();
        List<String> expected = feline.getFood("Хищник");
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
    }

    @Test
    void eatMeatReturnsException() throws Exception {
        Feline feline = new Feline();
        List<String> actual = feline.eatMeat();
        List<String> expected = feline.getFood("Хищник");
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
    }

    //getFamily
    @Test
    void getFamilyReturnsFelines() {
        Feline feline = new Feline();
        String actual = feline.getFamily();
        String expected = "Кошачьи";
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
    }

    //getKittens метод без аргументов
    @Test
    void getKittensWithoutCount() {
        Feline feline = new Feline();
        int actual = feline.getKittens();
        int expected = 1;
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
    }

    //testGetKittens метод с целым числом. Передадим 0, >0
    @Test
    void testGetKittensCountGreaterZero() {
        Feline feline = new Feline();
        int actual = feline.getKittens(5);
        int expected = 5;
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
    }

    @Test
    void testGetKittensCountZero() {
        Feline feline = new Feline();
        int actual = feline.getKittens(0);
        int expected = 0;
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
    }

    // тесты для getFood. Передадим: "Хищник","Травоядное", "Рыба", null

    @Test
    void getFoodHerbivoreSuccess() throws Exception {
        Feline feline = new Feline();
        List<String> actual = feline.getFood("Травоядное");
        List<String> expected = List.of("Трава", "Различные растения");
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);

    }

    @Test
    void getFoodPredatorSuccess() throws Exception{
        Feline feline = new Feline();
        List<String> actual = feline.getFood("Хищник");
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expected,actual, () -> "Expected: " + expected + ", but actual: " + actual);
    }

    @Test
    void getFoodUnknownAnimalException() throws Exception{
        Feline feline = new Feline();
        Throwable exception = assertThrows(
                Exception.class,
                () -> feline.getFood("Рыба"));
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник",exception.getMessage());
    }

    @Test
    void getFoodThrowsExceptionWithNull() throws Exception{
        Feline feline = new Feline();
        Throwable exception = assertThrows(
                Exception.class,
                () -> feline.getFood(null));
        assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник",exception.getMessage());

    }

}