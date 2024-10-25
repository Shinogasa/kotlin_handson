package org.example.helloworld

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

fun main(args: Array<String>) {
    println("Hello World!")

    val n = 20
    for (i in 1 .. n )
        println(fizzBuzz(i))
}