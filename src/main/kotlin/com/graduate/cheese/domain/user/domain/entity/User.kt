package com.graduate.cheese.domain.user.domain.entity

import com.graduate.cheese.domain.auth.domain.Role
import com.graduate.cheese.domain.image.domain.entity.Image
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    var id: UUID,

    @Column(length = 20)
    val email: String,

    @Column
    val nickName: String,

    @Column
    val password: String,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = [JoinColumn(name = "user_id")])
    var roles: MutableList<Role> = mutableListOf(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val image: List<Image>
)