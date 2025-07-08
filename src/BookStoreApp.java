import java.io.Console;
import java.util.ArrayList;

import Entities.*;
import Entities.Enums.BookType;
import ServiceContracts.IBooksService;
import ServiceContracts.IBuyBookService;
import ServiceContracts.IShippingService;
import Services.BooksService;
import Services.BuyBookService;
import Services.ShippingService;

public class BookStoreApp {
    private ArrayList<Book> inventory;
    private IBooksService _booksService;
    private IBuyBookService _buyBookService; 
    private IShippingService _shippingService;


    public BookStoreApp()
    {
        _booksService = new BooksService();
        _shippingService = new ShippingService();
        _buyBookService = new BuyBookService(_booksService, _shippingService);
        addingDefaultBooks();
        inventory = _booksService.getAllBooks();
    }
    public void run()
    {
        System.out.println("Welcome to the Quantum Bookstore System");

        boolean showBooks = true;
        while (true) {
            if (showBooks) {
                SelectedBook selectedBook = null;
                Book book;
                displayBooks();
                try {
                    selectedBook = SelectBook();
                }
                catch (IllegalArgumentException ex){
                    System.out.println("Error: " + ex.getMessage());
                    return;
                }
                book = selectedBook.book;
                if (book.getBookType() == BookType.SHOWCASE)
                {
                    System.out.println("ShowCase Books Not For Sale");
                }
                else
                {
                System.out.println("You will Buy " + (book.getBookType() == BookType.PAPERBOOK ?  
                selectedBook.selectedQuantity + "x " : "") + book.getTitle() + " by " + book.getAuthor() + ", total price " + book.getTotalPrice(selectedBook.selectedQuantity));
                System.out.println("Type Yes or Y to Confirm");
                String repsonse = System.console().readLine();
                if (repsonse.equalsIgnoreCase("yes") || repsonse.equalsIgnoreCase("y"))
                {
                    System.out.println("Please Enter Your Email: ");
                    var Email = System.console().readLine();
                    String address = null;

                    if (book.getBookType() == BookType.PAPERBOOK)
                    {
                        System.out.print("Enter Your Address: ");
                        address = System.console().readLine();
                    }
                    _buyBookService.BuyBook(book.getISBN(), selectedBook.selectedQuantity, Email, address);
                }
            }
        }
    }
}

    public void addingDefaultBooks()
    {
        ArrayList<Book> BooksList = new ArrayList<>();
        BooksList.add(new Book("1234", "Utopia", "Thomas More", 10, "1516", 50.0, BookType.PAPERBOOK));
        BooksList.add(new Book("12345", "Animal Farm", "George Orwell", 50, "1945", 50.0, BookType.PAPERBOOK));
        BooksList.add(new Book("12346", "Utopia", "Ahmed Khalid Tawfik", "2008", 50.0, BookType.EBOOK));
        BooksList.add(new Book("1", "ShowCaseBook", "Ahmed", 1, "2025", 1, BookType.SHOWCASE));
        for(Book book : BooksList){
            try {
                _booksService.AddBook(book);
            } 
            catch (IllegalArgumentException ex)
            {
                System.out.println("Error: " + ex.getMessage());
                return;
            }
        
        }
    }

    public void displayBooks()
    {
        if (inventory.isEmpty())
        {
            System.out.println("No Books Avilable Now");
            
        }

        else {
        System.out.println("Choose A book");
        for (int i = 0; i < inventory.size(); i++) {
        Book book = inventory.get(i);
        System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor() +
            " | " + book.getBookType() + " | " + (book.getBookType() == BookType.EBOOK ? "Unlimted" : book.getQuantity()));
        }
    }
}

    public SelectedBook SelectBook()
    {
        String input = System.console().readLine();
        if (input == null || input.trim().isEmpty())
        {
            throw new IllegalArgumentException("You should choose a book");
        }
        int selectedBookIndex = Integer.parseInt(input) - 1;
        if (selectedBookIndex > inventory.size() - 1 || selectedBookIndex < 0) {
            throw new IllegalArgumentException("Invalid Selection");
        }
        Book book = inventory.get(selectedBookIndex);
        if (book.getBookType() == BookType.PAPERBOOK) {
            System.out.println("Please Enter the quantity:");
            int quantity = Integer.parseInt(System.console().readLine());
            return new SelectedBook(book, quantity);
        }
        return new SelectedBook(book, 1);
    }
}

