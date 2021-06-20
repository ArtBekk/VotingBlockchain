package com.artbekk.voting

import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface BlockRepository : CrudRepository<Block, String> {
}

interface DocumentRepository : CrudRepository<Document, String> {
}

@RepositoryRestResource
interface NodeRepository : CrudRepository<Node, String> {
}

interface UserRepository : CrudRepository<User, String> {
}

@RepositoryRestResource
interface VoteRepository : CrudRepository<Vote, Long> {
}