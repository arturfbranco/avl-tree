package src;

import java.time.LocalDate;

public class Person {

    private String cpf;
    private String rg;
    private String name;
    private LocalDate birth;

    private String birthCity;

    public Person(String cpf, String rg, String name, LocalDate birth, String birthCity) {
        this.cpf = cpf;
        this.rg = rg;
        this.name = name;
        this.birth = birth;
        this.birthCity = birthCity;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }
}
