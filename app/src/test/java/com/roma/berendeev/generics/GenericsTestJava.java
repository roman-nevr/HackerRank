package com.roma.berendeev.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/// https://habr.com/en/company/sberbank/blog/416413/
public class GenericsTestJava {

    @Test
    public void covariance() {
        //ковариантность - сохранение наследования в производных типах
        //массивы коварианты
        Animal[] animals = new Animal[5];
        Cat[] cats = new Cat[5];
        animals = cats;
        Animal[] someAnimals = new Cat[5];
        //в этом есть проблема, потому, что
        someAnimals[0] = new Dog(); // падение в рантайме при попытке присвоить элементу массива Cats[] значение Dog
        //ps студия показывает ошибки, но опираться на это нельзя
    }

    @Test
    public void invariance() {
        //инвариантность - отсутствие наследования в производных типах
        //листы инвариантны
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
//        animals = cats;
//        List<Animal> cats2 = new ArrayList<Cat>();
        //мы не можем присвоить списку Animal значение список Cat и избегаем ошибки в рантайме
    }

    @Test
    public void wildcards_covariance() {
        //но бывает так, что нам нужно такое присвоение
        //и мы не хотим терять проверку типов
        //воспользуемся wildcards
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        List<? extends Animal> animals = cats;
        //но что лежит в списке animals?
        Animal animal = animals.get(0);
        //на самом деле это любой потомок Animal, но обращаться к нему можно только как к Animal
        //это ковариантность на wildcards

        //а что мы можем положить в список animals?
//        animals.add(new Animal());
//        animals.add(new Cat());
        //а ничего
    }

    @Test
    public void wildcards_contravariant() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        List<? super Cat> cats = animals;
        //мы можем добавить в список Cat самого Cat или его наследника Kitty
        cats.add(new Cat());
        cats.add(new Kitty());
        //но что лежит в списке cats?
//        Cat animal = cats.get(0);
        Object animal = cats.get(0); // обратиться к элементу можно только как к Object'у
        //это контрвариантность на wildcards

        //для облегчения понимания надо запомнить буквы PECS
        //producer - extends, consumer - super
        //producer выдает элементы
        //consumer потребляет
    }

    @Test
    public void raw_type() {
        List<?> rawList = new ArrayList<>(); //? это <? extends Object>
//        rawList.add(new Object()); мы не можем ничего добавить, но можем присвоить
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        rawList = animals;
        rawMethod(rawList);
        rawMethod(animals);

    }

    private void rawMethod(List<?> rawList) {}

    private <T> void genericMethod(List<T> list) {}

    class Animal {}

    class Cat extends Animal {}

    class Dog extends Animal {}

    class Kitty extends Cat {}
}
