package com.lynas.app.controller

import com.lynas.app.Book
import com.lynas.app.BookRequest
import com.lynas.app.BookService
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(
    val bookService: BookService
) {
    @GetMapping("/")
    fun getAllBooks(): List<Book> = bookService.findAll()

    @GetMapping("/{bookId}")
    fun getBookById(@PathVariable bookId: UUID): Book =
        bookService.findBookById(bookId) ?: throw ResourceNotFoundException("Book not found by $bookId")

    @PostMapping("/")
    fun createBook(@RequestBody request: BookRequest): Book {
        return bookService.createBook(request)
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(message: String?) : RuntimeException(message)