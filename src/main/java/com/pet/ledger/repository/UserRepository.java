package com.pet.ledger.repository;

import com.pet.ledger.model.type.User;
import com.pet.ledger.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ModelRepository<User>, JpaSpecificationExecutor<User>, UserRepositoryCustom {
    User findAllByEmail(String email);

    @Query(value = "SELECT email, users.id, name, given_name, family_name, picture, phone_number\n"
            + "  FROM users INNER JOIN members on users.id = members.user_id where members.course_id=:courseId"
            + " and users.email like %:email%"
            , nativeQuery = true)
    List<User> listUserByCourse(String courseId, String email);
}
