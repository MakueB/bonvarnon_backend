package ru.makiev.application.dto

import kotlinx.serialization.Serializable
import nonapi.io.github.classgraph.json.Id

@Serializable
data class UpdateAdditiveRequest(
    val id: Int,
    val name: String? = null,
    val extraPrice: String? = null
)
