package com.the.basic.tech.info.Repositories;

import com.the.basic.tech.info.Entity.tabelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface tabelUserRepository extends JpaRepository<tabelUser,String> {
    @Query(value = "SELECT COUNT(*) AS total FROM tabel_user WHERE username =:username OR email =:email" , nativeQuery = true)
    Integer getExistUser(String username, String email);
    @Query(value = "SELECT * FROM tabel_user WHERE username =:username" , nativeQuery = true)
    tabelUser validateUser(String username);

}
