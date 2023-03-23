package com.the.basic.tech.info.Repositories;

import com.the.basic.tech.info.Entity.testGrit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface mainRepository extends JpaRepository<testGrit,String> {

    @Query(value = "SELECT * FROM test3 ORDER BY id DESC" , nativeQuery = true)
    List<testGrit> getAllData();

    @Query(value = "SELECT * FROM test3 ORDER BY id DESC LIMIT 1" , nativeQuery = true)
    testGrit getLastData();

}
