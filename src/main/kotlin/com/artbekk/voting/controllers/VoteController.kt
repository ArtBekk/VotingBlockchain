package com.artbekk.voting.controllers

import com.artbekk.voting.*
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@RestController
class VoteController(
    private val userRepo: UserRepository,
    private val blockRepo: BlockRepository,
    private val voteRepo: VoteRepository
) {
    @GetMapping("/vote")
    @Transactional
    fun vote(
        model: Model,
        @RequestParam hashedDocument: String?,
        @RequestParam hashedName: String?,
        @RequestParam value: String?
    ): String {
        if (hashedDocument != null) {
            if(hashedName != null) {
                val user = userRepo.findById(hashedDocument).get()
                if (user.hashedFullName == hash(hashedName + user.saltForName)) {
                    if (voteRepo.findAll().any { vote -> vote.userID.hashedDocument == hashedDocument })
                        return "You've already voted"
                    else {
                        //getting last block
                        val lastBlock = blockRepo.findAll().last()
                        //getting votes from last block
                        val lastBlockVotes =
                            voteRepo.findAll().filter { vote -> vote.userID.hashedDocument == hashedDocument }
                        if (value != null) {
                            if (lastBlockVotes.size > 7) {

                                //creating new Block
                                lastBlock.merkleRoot = hash(lastBlockVotes.toString())
                                val newBlock = Block(lastBlock.nonce++, hash(lastBlock.toString()))
                                blockRepo.save(newBlock)

                                //creating new Vote
                                val newVote = Vote(user, newBlock.nonce, value)
                                voteRepo.save(newVote)

                                return "Success"
                            } else {
                                val newVote = Vote(user, lastBlock.nonce, value)
                                voteRepo.save(newVote)

                                return "Success"
                            }
                        } else
                            return "No voting value"
                    }
                }
            }
            else {
                return "No such user"
            }
            return "Incorrect document"
        }
        return "Specify document"
    }
}