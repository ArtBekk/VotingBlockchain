package com.artbekk.voting.controllers

import com.artbekk.voting.VoteRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckVoteController(private val voteRepository: VoteRepository) {

    @GetMapping("/check")
    @Transactional
    fun vote(model: Model, @RequestParam document: String?): String {
        if (voteRepository.findAll().any { vote -> vote.userID.hashedDocument == document })
        {
            val foundVote = voteRepository.findAll().find { vote -> vote.userID.hashedDocument == document }
            return "You voted for: " + foundVote!!.value
        }
        return "You haven't voted yet"
    }
}