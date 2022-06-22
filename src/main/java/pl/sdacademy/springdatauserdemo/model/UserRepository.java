package pl.sdacademy.springdatauserdemo.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllByLastName(String lastName);

    List<User> findAllByLastNameAndFirstName(String lastName, String firstName);

    User findDistinctByLastNameAndFirstName(String lastName, String firstName);

    @Query("SELECT u from User u where u.lastName = :lastName")
    List<User> findAllByLastNameQuery(@Param("lastName") String lastName);
}
