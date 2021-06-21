package com.artbekk.voting.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainPageController() {

    @GetMapping("/")
    fun vote(model: Model, @RequestParam document: String?): String {
        return "index"
    }
}
