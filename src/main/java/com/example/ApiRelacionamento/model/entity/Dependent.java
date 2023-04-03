
package com.example.ApiRelacionamento.model.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dependente")
public class Dependent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String name;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "data_nacimento")
    private Date birth;
    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
