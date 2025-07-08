package ServiceContracts;
import java.util.ArrayList;
import java.util.List;
import Entities.Book;

public interface IBooksService {
    void AddBook(Book book);
    Book GetBook(String ISBN);
    ArrayList<Book> getAllBooks();
    void RemoveBook(String ISBN);
    List<Book> RemoveOutDatedBooks(String year);
}

