package com.artbekk.voting

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Block(
    @Id var nonce: Int,
    var hashedPrevNode: String,
    var merkleRoot: String? = "",
    var localDateTime: LocalDateTime = LocalDateTime.now(),
)

@Entity
class Document(
    @Id var hashedValue: String,
    var type: String,
)

@Entity
class Node(
    @Id var ipAddress: String,
    var nodeName: String
)

@Entity
class User(
    @Id var hashedDocument: String,
    var hashedFullName: String,
    var saltForName: String
)

@Entity
class Vote(
    @OneToOne var userID: User,
    var blockNonce: Int,
    @Id @GeneratedValue val id: Long? = null// hashedDocument
)