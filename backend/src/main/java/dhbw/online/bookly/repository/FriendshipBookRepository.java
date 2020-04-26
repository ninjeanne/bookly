package dhbw.online.bookly.repository;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendshipBookRepository extends JpaRepository<FriendshipBook, String> {

    Optional<FriendshipBook> findByUser(User user);

    void deleteByUser(User user);

    boolean existsByUser(User user);
}
