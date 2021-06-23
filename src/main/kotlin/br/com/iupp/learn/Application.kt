package br.com.iupp.learn

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.iupp.learn")
		.start()
}