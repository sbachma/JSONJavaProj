import java.util.Arrays;
import java.util.List;

public class Book {

    protected long isbn;
    protected String title;
    protected  String edition;
    protected int pages;
    protected double price;
    protected List<String> author;
    protected boolean audio;

    public Book(long isbn, String title, String edition, int pages, double price, List<String> author, boolean audio) {
        this.isbn = isbn;
        this.title = title;
        this.edition = edition;
        this.pages = pages;
        this.price = price;
        this.author = author;
        this.audio = audio;
    }

    public Book() {
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public boolean getAudio() {
        return audio;
    }

    public void setAudio(boolean audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", edition='" + edition + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", author=" + author +
                ", audio=" + audio +
                '}';
    }
}
