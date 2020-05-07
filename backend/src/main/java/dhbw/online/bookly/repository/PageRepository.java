package dhbw.online.bookly.repository;

import dhbw.online.bookly.dto.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, String> {

    @Transactional
    Optional<Page> findByUuid(int uuid);

    boolean existsByUuid(int uuid);

}
