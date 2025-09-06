package ru.makiev.application.exception

class ValidationException(val errors: List<String>) : RuntimeException()