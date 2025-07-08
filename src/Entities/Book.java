package Entities;

import Entities.Enums.BookType;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    public int quantity = 0;
    private String publishYear;
    private double price;
    private BookType bookType;
    
    public Book(String ISBN, String title, String author, int quantity, String publishYear, double price, BookType bookType) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.publishYear = publishYear;
        this.price = price;
        this.bookType = bookType;
    }

    // overload for ebook
    public Book(String ISBN, String title, String author, String publishYear, double price, BookType bookType){
    this(ISBN, title, author, 0, publishYear, price, bookType);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newQuantity)
    {
        quantity = newQuantity;
    }

    public String getISBN() {
        return ISBN;
    }

    public BookType getBookType(){
        return bookType;
    }
    public String getPublisherYear() {
        return publishYear;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice(int quantity)
    {
        return price * quantity;
    }
}

