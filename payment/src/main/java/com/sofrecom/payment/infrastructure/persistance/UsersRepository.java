package com.sofrecom.payment.infrastructure.persistance;

import com.sofrecom.payment.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {

  @Query("select t from User t where t.name =:name")
  List<User> findByName(@Param("name") String name);
}
