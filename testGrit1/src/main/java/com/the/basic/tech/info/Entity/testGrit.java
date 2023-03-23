package com.the.basic.tech.info.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Setter
@Getter
@Entity
@Table (name = "testGrit")
public class testGrit implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "city", nullable = true)
    private String city;
    @Column(name = "hobby", nullable = true)
    private String hobby;
    @Column(name = "age", nullable = true)
    private Integer age;
    @Column(name = "marriage", nullable = true)
    private String marriage;

}
