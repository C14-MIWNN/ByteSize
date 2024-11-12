package nl.miwnn.se14.bytesize.repositories;

import nl.miwnn.se14.bytesize.model.ByteSizeUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Heron
 * Purpose for the class
 */
public interface ByteSizeUserRepository extends JpaRepository<ByteSizeUser, Long> {

}
