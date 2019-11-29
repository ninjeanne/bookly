package dhbw.online.bookly.repository;

import dhbw.online.bookly.dto.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageRepository extends CrudRepository<Page, String> {

    Optional<Page> findByUuid(int uuid);

    boolean existsByUuid(int uuid);

}
