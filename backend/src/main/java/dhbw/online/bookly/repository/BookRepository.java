package dhbw.online.bookly.repository;

import dhbw.online.bookly.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
