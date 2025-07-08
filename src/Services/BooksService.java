package Services;

import Entities.Book;
import Entities.Enums.BookType;

import java.util.ArrayList;
import java.util.List;

import ServiceContracts.IBooksService;


public class BooksService implements IBooksService {

    ArrayList<Book> inventory;
    public BooksService() {
        this.inventory = new ArrayList<Book>();
    }
    public void AddBook(Book book) {
        if (book == null)
        {
            throw new IllegalArgumentException("book cannot be null");
        }
        inventory.add(book);
    }

    public Book GetBook(String ISBN) {
        return inventory.stream().filter(book -> book.getISBN().equals(ISBN)).findFirst().orElse(null);
    }

    public ArrayList<Book> getAllBooks()
    {
        return inventory;
    }

    public void RemoveBook(String ISBN) {
        inventory.removeIf(book -> book.getISBN().equals(ISBN));
    }

    public List<Book> RemoveOutDatedBooks(String year) {
        List<Book> outDatedBooks = inventory.stream().filter(book -> Integer.parseInt(book.getPublisherYear()) < Integer.parseInt(year)).toList();
        inventory.removeAll(outDatedBooks);
        return outDatedBooks;
    }
}
