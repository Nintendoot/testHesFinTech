package by.nintendoot.testhesfintech.repository;

import by.nintendoot.testhesfintech.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    UserAccount findUserAccountByUsername(String username);
    boolean existsUserAccountByUsername(String username);
}
