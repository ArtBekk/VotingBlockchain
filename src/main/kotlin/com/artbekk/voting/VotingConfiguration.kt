package com.artbekk.voting

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VotingConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            voteRepository: VoteRepository,
                            nodeRepository: NodeRepository,
                            blockRepository: BlockRepository) = ApplicationRunner {

        //Adding users
        val sampleUser1 = User(hash("SampleDoc1"), hash(hash("SampleName1")+"SampleSalt1"), "SampleSalt1")

        //hashedName 68626bcb30b72e9c30a9fa3811810fb754dfde1e9c08444d28225c0c7c6de479
        val sampleUser2 = User(hash("SampleDoc2"), hash(hash("SampleName2")+"SampleSalt2"), "SampleSalt2")
        userRepository.save(sampleUser1)
        userRepository.save(sampleUser2)

        //Adding vote
        voteRepository.save(Vote(sampleUser1, 1, "За КПРФ!"))

        //Adding block
        blockRepository.save(Block(1, "Initial hash"))

        //Adding node
        nodeRepository.save(Node("46.180.35.104", "ArtBekk's Node"))
    }
}