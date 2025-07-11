package br.com.jeb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;
    @Column(name = "model_year", nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToMany
    @JoinTable(name = "car_acessorio", joinColumns = @JoinColumn(name = "car_id"), inverseJoinColumns = @JoinColumn(name = "acessorio_id"))
    private Set<Acessorio> acessorios = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Set<Acessorio> acessorios) {
        this.acessorios = acessorios;
    }
}
