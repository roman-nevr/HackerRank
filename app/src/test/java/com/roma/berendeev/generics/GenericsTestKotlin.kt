package com.roma.berendeev.generics

import org.junit.Test

class GenericsTestKotlin {

    @Test
    fun kotlin_wildcard() {
        val animals: List<Animal> = arrayListOf()
    }

    class Vet {

        private fun <T: Animal> vet(animal: Wrapper<out T>): Wrapper<in T> {
            val animal: Animal = animal.value
            return Wrapper(animal)
        }
    }

    class Wrapper<T> (
            var value: T
    )

    inline fun <reified T: Animal> vet(animal: Animal) {
        if (animal is Cat) {

        }
    }

    open class Animal

    open class Cat: Animal()
    class Kitty: Cat()

    class Dog: Animal()
}