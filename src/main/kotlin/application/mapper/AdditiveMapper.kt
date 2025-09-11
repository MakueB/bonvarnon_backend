package ru.makiev.application.mapper

import ru.makiev.application.dto.additive.AdditiveResponse
import ru.makiev.domain.model.Additive

fun Additive.toAdditiveResponse() = AdditiveResponse(id, name, extraPrice.toPlainString())

