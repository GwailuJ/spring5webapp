package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(PublisherRepository publisherRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception{

        Publisher ladybird = new Publisher("LadyBird Publishing","12 London rd.", "London", "London", 12);

        publisherRepository.save(ladybird);

        Author eric = new Author("Eric", "Evens");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(ladybird);
        ladybird.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(ladybird);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(ladybird);
        ladybird.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(ladybird);

        /*ladybird.getAuthors().add(eric);
        ladybird.getAuthors().add(rod);
        eric.setPublisher(ladybird);
        rod.setPublisher(ladybird);*/

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books " + bookRepository.count());
        System.out.println("Publisher Number of books " + ladybird.getBooks().size());
    }
}
