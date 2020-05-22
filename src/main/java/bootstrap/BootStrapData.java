package bootstrap;

import model.Author;
import model.Book;
import model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repositories.AuthorRepository;
import repositories.BookRepository;
import repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    @Autowired
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, repositories.PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric","Evans");
        Book book = new Book("Turf Cutting","12345");
        Publisher publisher = new Publisher("Go Books","add1","add2","TX","12345");

        eric.getBooks().add(book);
        book.getAuthors().add(eric);
        book.setPublisher(publisher);

        publisher.getBooks().add(book);


        authorRepository.save(eric);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        System.out.println("Number of books =" + bookRepository.count());
        System.out.println("Number of authors =" + authorRepository.count());
        System.out.println("Number of publishers =" + publisherRepository.count());
    }
}
