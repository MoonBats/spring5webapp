package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("Pub1");
        publisher.setAddressLine1("add1");
        publisher.setAddressLine2("add2");
        publisher.setState("Texas");
        publisher.setZip("45632");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric","Evans");
        Book book = new Book("Turf Cutting","12345");
        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        book.setPublisher(publisher);

        publisher.getBooks().add(book);
        //publisherRepository.save(publisher);

        authorRepository.save(eric);
        bookRepository.save(book);
        publisherRepository.save(publisher);



        System.out.println("Number of books =" + bookRepository.count());
        System.out.println("Number of authors =" + authorRepository.count());
        System.out.println("Number of publishers =" + publisherRepository.count());
    }
}
