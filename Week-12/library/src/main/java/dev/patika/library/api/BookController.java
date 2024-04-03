package dev.patika.library.api;

import dev.patika.library.business.abstracts.IBookService;
import dev.patika.library.core.config.modelMapper.IModelMapperService;
import dev.patika.library.core.result.Result;
import dev.patika.library.core.result.ResultData;
import dev.patika.library.core.utilities.ResultHelper;
import dev.patika.library.dto.request.author.AuthorSaveRequest;
import dev.patika.library.dto.request.author.AuthorUpdateRequest;
import dev.patika.library.dto.request.book.BookSaveRequest;
import dev.patika.library.dto.request.book.BookUpdateRequest;
import dev.patika.library.dto.response.CursorResponse;
import dev.patika.library.dto.response.author.AuthorResponse;
import dev.patika.library.dto.response.book.BookResponse;
import dev.patika.library.entities.Author;
import dev.patika.library.entities.Book;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final IBookService bookService;
    private final IModelMapperService modelMapper;
    public BookController(IBookService bookService, IModelMapperService modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest){
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest,Book.class);
        this.bookService.save(saveBook);
        return ResultHelper.createdData(this.modelMapper.forResponse().map(saveBook,BookResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id){
        Book book= this.bookService.get(id);
        return ResultHelper.successData(this.modelMapper.forResponse().map(book,BookResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page",required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "10") int pageSize
    ){
        Page<Book> bookPage = this.bookService.cursor(page,pageSize);
        Page<BookResponse> bookResponsePage = bookPage
                .map(book -> this.modelMapper.forResponse().map(book,BookResponse.class));
        return ResultHelper.cursor(bookResponsePage);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest){
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest,Book.class);
        this.bookService.update(updateBook);
        return ResultHelper.successData(this.modelMapper.forResponse().map(updateBook,BookResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.bookService.delete(id);
        return ResultHelper.ok();
    }
}
