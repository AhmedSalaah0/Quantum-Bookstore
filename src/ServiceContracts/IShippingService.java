package ServiceContracts;
import java.util.List;
import Entities.*;

public interface IShippingService {
    void bookShipping(Book book, String Address);
}
