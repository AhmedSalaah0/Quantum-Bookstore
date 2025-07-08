package Services;

import Entities.Book;
import ServiceContracts.IShippingService;

public class ShippingService implements IShippingService{

    public void bookShipping(Book book, String Address) {
        System.out.println("This Book Shipped To you");
        
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        System.out.println("To Address: " + Address);
    }
    
}
