package ServiceContracts;

public interface IBuyBookService {
    void BuyBook(String ISBN, int quantity, String customerEmail, String address);
}
