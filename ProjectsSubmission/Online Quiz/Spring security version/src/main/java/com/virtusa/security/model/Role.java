package com.virtusa.security.model;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "rolemember")
public class Role {
    private Long id;
    private String name;
    private Set<Member> mems;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<Member> getUsers() {
        return mems;
    }

    public void setUsers(Set<Member> mems) {
        this.mems = mems;
    }
}