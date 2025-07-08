package Services;

import Entities.Book;
import Entities.Enums.BookType;
import ServiceContracts.*;

public class BuyBookService implements IBuyBookService {
    private IBooksService _booksService;
    private IShippingService _shippingService;
    public BuyBookService(IBooksService booksService, IShippingService shippingService) {
        _booksService = booksService;
        _shippingService = shippingService;
    }
    public void BuyBook(String ISBN, int quantity, String customerEmail, String address) {
        if (ISBN == null || ISBN.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        Book book = _booksService.GetBook(ISBN);
        if (book == null) {
            throw new IllegalArgumentException("Book with ISBN " + ISBN + " does not exist");
        }

        if (book.getBookType() == BookType.EBOOK) {
            // should send an email to the customer with the eBook details
            System.out.println("EBook purchased: " + book.getTitle() + " by " + book.getAuthor() + 
                                ". A confirmation email has been sent to " + customerEmail);
        }
        else if (book.getBookType() == BookType.PAPERBOOK) {
            if (book.getQuantity() < quantity)
            {
                throw new IllegalArgumentException("Not enough stock for book with ISBN " + ISBN);
            }
            _shippingService.bookShipping(book, address);
            System.out.println("purchased Successfully A confirmation email has been sent to your email");
            book.setQuantity(book.getQuantity() - quantity);
        } else {
            throw new IllegalArgumentException("Unknown book type for ISBN: " + ISBN);
        }
    }
}
