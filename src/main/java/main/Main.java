package main;

import config.SpringConfig;
import entity.BookDetailsEntity;
import entity.BookEntity;
import entity.CategoryEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.BookRepository;
import repositories.CategoryRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by tuananh on 06/06/17.
 */
public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");
    static CategoryRepository categoryRepository = (CategoryRepository) context.getBean("categoryRepository");

    public static void main(String[] args) {
        createNewBookEntryWithNewCategory();

        createNewBookEntry();

        findByAuthor("Roger");

        findByNameAndAuthor("linux", "Roger");

        findByNameOrAuthor("linux", "Roger");

        findByPriceLessThan(80);

        findByBookDetailsIsbn("ISIBF1219321");

        findByNameContaining("Nu");

        // Demo how to use JPQL
        findAllUsingQuery();

        findBookNameStartWithUsingQuery("Java");

        findBookPriceGreaterThanUsingQuery(80);
    }

    public static void findByAuthor(String author) {
        List<BookEntity> bookEntityList = bookRepository.findByAuthor(author);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books which author = " + author);
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameAndAuthor(String name, String author) {
        List<BookEntity> bookEntityList = bookRepository.findByNameAndAuthor(name, author);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books which name = " + name + " and author = " + author );
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameOrAuthor(String name, String author) {
        List<BookEntity> bookEntityList = bookRepository.findByNameOrAuthor(name, author);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books which name = " + name + " or author = " + author );
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByPriceLessThan(int price) {
        List<BookEntity> bookEntityList = bookRepository.findByBookDetailsPriceLessThan(price);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books price less than " + price);
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findByNameContaining(String name) {
        List<BookEntity> bookEntityList = bookRepository.findByNameContaining(name);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books which containing name = " + name);
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findAllUsingQuery() {
        List<BookEntity> bookEntityList = bookRepository.getAll();
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books");
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findBookNameStartWithUsingQuery(String name) {
        List<BookEntity> bookEntityList = bookRepository.getBookNameStartWith(name);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books");
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }

    public static void findBookPriceGreaterThanUsingQuery(int price) {
        List<BookEntity> bookEntityList = bookRepository.getBookPriceGreaterThan(price);
        if (bookEntityList != null) {
            System.out.println("\nFind " + bookEntityList.size() + " books");
            for (BookEntity bookEntity: bookEntityList) {
                System.out.println(bookEntity.toString());
            }
        }
    }


    public static void findByBookDetailsIsbn(String isbn) {
        BookEntity bookEntity = bookRepository.findByBookDetailsIsbn(isbn);
        if (bookEntity != null) {
            System.out.println("\nFind book which isbn = " + isbn);
            System.out.println(bookEntity.toString());
        }
    }

    public static void createNewBookEntry() {
        // khởi tạo object Category, với id = 1
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1);

        BookEntity bookEntity = createNewBook();
        // ?? l?u object Book xu?ng table book, ph?i reference object book này v?i 1 category có id = 1
        // l?u 1 book có category id = 1
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }

    public static void createNewBookEntryWithNewCategory() {
        // kh?i t?o object Category, sau ?ó save xu?ng table category
        CategoryEntity categoryEntity = createNewCatorgy();
        categoryRepository.save(categoryEntity);

        BookEntity bookEntity = createNewBook();
        // ?? l?u object Book xu?ng table book, ph?i reference object book này v?i 1 category m?i t?o.
        bookEntity.setCategory(categoryEntity);
        bookRepository.save(bookEntity);
    }

    private static CategoryEntity createNewCatorgy() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("IT");
        categoryEntity.setDescription("IT books");
        return categoryEntity;
    }

    private static BookEntity createNewBook() {
        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setIsbn("ISIBF1219323");
        bookDetailsEntity.setNumberOfPage(23);
        bookDetailsEntity.setPrice(65);
        bookDetailsEntity.setPublishDate(new Date());

        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Java A-Z");
        bookEntity.setAuthor("Roger");
        bookEntity.setBookDetails(bookDetailsEntity);
        bookDetailsEntity.setBook(bookEntity);

        return bookEntity;
    }

}



