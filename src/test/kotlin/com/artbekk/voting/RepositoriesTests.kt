package com.artbekk.voting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val documentRepository: DocumentRepository,
    val voteRepository: VoteRepository,
    val blockRepository: BlockRepository
) {

    @Test
    fun findUser() {
        val document = Document(hash("3212185946"), "Паспорт")
        entityManager.persist(document)

        val artbekk = User(hash("3212185946"), hash(hash("Бекк Артур Евгеньевич") + "someSalt123"), "someSalt123")
        entityManager.persist(artbekk)

        val initialBlock = Block(1, "initialBlockValue")
        entityManager.persist(initialBlock)

        val vote = Vote(artbekk, 1)
        entityManager.persist(vote)

        entityManager.flush()
        val queryResult = userRepository.findById(artbekk.hashedDocument)
        val foundUser: User = queryResult.get()
        assertThat(foundUser).isEqualTo(artbekk)
    }

    /* @Test
     fun `When findByLogin then return User`() {
         val juergen = User("springjuergen", "Juergen", "Hoeller")
         entityManager.persist(juergen)
         entityManager.flush()
         val user = userRepository.findByLogin(juergen.login)
         assertThat(user).isEqualTo(juergen)
     }*/
}