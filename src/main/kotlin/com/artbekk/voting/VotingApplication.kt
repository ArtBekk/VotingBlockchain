package com.artbekk.voting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.persistence.EntityManager

@SpringBootApplication
class VotingApplication () {
}

fun main(args: Array<String>) {
    runApplication<VotingApplication>(*args)
}