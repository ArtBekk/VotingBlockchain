package com.artbekk.voting.controllers

import com.artbekk.voting.BlockRepository
import com.artbekk.voting.UserRepository
import com.artbekk.voting.VoteRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/vote")
class VoteController (private val userRepo: UserRepository, private val blockRepo: BlockRepository, private val voteRepository: VoteRepository) {

}