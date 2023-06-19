package com.graduate.cheese

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CheeseApplication

fun main(args: Array<String>) {
	runApplication<CheeseApplication>(*args)
}
