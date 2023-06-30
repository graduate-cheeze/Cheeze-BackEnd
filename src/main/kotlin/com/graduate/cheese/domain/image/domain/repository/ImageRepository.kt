package com.graduate.cheese.domain.image.domain.repository

import com.graduate.cheese.domain.image.domain.entity.Image
import org.springframework.data.repository.CrudRepository

interface ImageRepository : CrudRepository<Image, Long> {
}