import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Book> books;
    public static void main(String[] args) {
        int menuChoice = 0;
        deserialize();
        do{
            menuChoice = menu(menuChoice);
            if(menuChoice == 1){
                create();
            }else if(menuChoice == 2){
                display();
            }else if(menuChoice == 3){
                edit();
            }else if(menuChoice == 4){
                delete();
            }
        } while(menuChoice != 0);
        serialize();
    }
    private static void serialize() {
        Gson gson = new Gson();
        String json = gson.toJson(books);
        try {
            Files.write(Paths.get("jsonpartnerexercise.json"), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deserialize(){
        try{
            Reader reader = Files.newBufferedReader(Paths.get("jsonpartnerexercise.json"));
            books = new Gson().fromJson(reader, new TypeToken<List<Book>>() {}.getType());
            reader.close();
            for(Book book : books){
                System.out.println(book);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void create(){
        Book userBook = new Book();
        System.out.println("Enter isbn code: ");
        if(scanner.hasNextLong()){
            userBook.setIsbn(scanner.nextLong());
            scanner.nextLine();
        } else{
            System.out.println("Enter a positive long next time");
        }
        System.out.println("Enter title: ");
        userBook.setTitle(scanner.nextLine());
        System.out.println("Enter edition: ");
        userBook.setEdition(scanner.nextLine());
        System.out.println("Enter pages: ");
        if(scanner.hasNextInt()){
            userBook.setPages(scanner.nextInt());
            scanner.nextLine();
        } else{
            System.out.println("Enter a positive number next time");
        }
        System.out.println("Enter price: ");
        if(scanner.hasNextDouble()){
            userBook.setPrice(scanner.nextDouble());
            scanner.nextLine();
        }else {
            System.out.println("Enter a positive double next time");
        }
        System.out.println("Enter number of authors: ");
        int numAuthors = 0;

        if(scanner.hasNextDouble()){
            numAuthors = scanner.nextInt();
            scanner.nextLine();
        }else {
            System.out.println("Enter a positive int next time");
        }
        int j = 0;
        List<String> authors = new ArrayList<>();
        while(j < numAuthors)
        {
            System.out.println("Enter author " + j);
            authors.add(j, scanner.nextLine());
            j++;
        }
        userBook.setAuthor(authors);
        System.out.println("Is there audio (y/n): ");
        userBook.setAudio(scanner.nextLine().equals("y"));
        books.add(userBook);
        serialize();
    }
    public static void display(){
        deserialize();
    }
    public static void edit(){
        long userIsbn = 0;
        if(scanner.hasNextLong()){
            userIsbn = scanner.nextLong();
            scanner.nextLine();
        } else {
            System.out.println("Enter a positive long next time");
        }
        for(Book book : books){
            if(userIsbn == book.getIsbn()) {
                System.out.println("What would you like to edit: ");
                System.out.println("1 for title, 2 for edition, 3 for pages, 4 for price, 5 for authors, 6 for audio: ");
                if (scanner.hasNextInt()) {
                    switch (scanner.nextInt()) {
                        case 1 -> {
                            scanner.nextLine();
                            System.out.println("Enter title: ");
                            book.setTitle(scanner.nextLine());
                        }
                        case 2 -> {
                            scanner.nextLine();
                            System.out.println("Enter edition: ");
                            book.setEdition(scanner.nextLine());
                        }
                        case 3 -> {
                            scanner.nextLine();
                            System.out.println("Enter pages: ");
                            if (scanner.hasNextInt()) {
                                book.setPages(scanner.nextInt());
                                scanner.nextLine();
                            } else {
                                System.out.println("Enter a positive integer next time");
                            }
                        }
                        case 4 -> {
                            scanner.nextLine();
                            System.out.println("Enter price: ");
                            if (scanner.hasNextDouble()) {
                                book.setPrice(scanner.nextDouble());
                                scanner.nextLine();
                            } else {
                                System.out.println("Enter a positive double next time");
                            }
                        }
                        case 5 -> {
                            scanner.nextLine();
                            System.out.println("Enter number of authors: ");
                            int numAuthors = 0;
                            if (scanner.hasNextInt()) {
                                numAuthors = scanner.nextInt();
                                scanner.nextLine();
                            } else {
                                System.out.println("Enter a positive integer next time");
                            }
                            int j = 0;
                            List<String> authors = new ArrayList<>();
                            while (j < numAuthors) {
                                System.out.println("Enter author " + j);
                                authors.add(j, scanner.nextLine());
                                j++;
                            }
                            book.setAuthor(authors);
                        }
                        case 6 -> {
                            scanner.nextLine();
                            System.out.println("Is there audio (y/n): ");
                            book.setAudio(scanner.nextLine().equals("y"));
                        }
                    }
                }
            }
        }
        serialize();
    }
    public static void delete(){
        long userIsbn = 0;
        if(scanner.hasNextLong()){
            userIsbn = scanner.nextLong();
        } else {
            System.out.println("Enter a positive number next time");
        }
        int i = 0;
        for(Book book : books){
            if(userIsbn == book.getIsbn())
            {
                books.remove(i);
                break;
            }
            i++;
        }
        serialize();
    }
    public static int menu(int menuChoice){
        System.out.println("1: Create Book");
        System.out.println("2: Display Book");
        System.out.println("3: Edit Book");
        System.out.println("4: Delete Book");
        System.out.println("0: Exit");
        if(scanner.hasNextInt()){
            menuChoice = scanner.nextInt();
        } else {
            System.out.println("Enter positive integer");
        }
        scanner.nextLine();
        switch (menuChoice) {
            case 1 -> System.out.println("Input Data for new Book: ");
            case 2 -> System.out.println("Enter Book ISBN number to display: ");
            case 3 -> System.out.println("Enter ISBN number of book to edit: ");
            case 4 -> System.out.println("Enter ISBN number of book to delete: ");
        }
        return menuChoice;
    }
}