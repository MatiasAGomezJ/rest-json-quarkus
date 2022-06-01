package edu.poniperro.restjson.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Developer extends PanacheEntity {

    @Column(unique = true)
    public String name;

    @Column
    public Integer age;
}
