package Entities;

public class SelectedBook {
    public Book book;
    public int selectedQuantity;

    public SelectedBook(Book book, int selectedQuantity) {
        this.book = book;
        this.selectedQuantity = selectedQuantity;
    }
}
