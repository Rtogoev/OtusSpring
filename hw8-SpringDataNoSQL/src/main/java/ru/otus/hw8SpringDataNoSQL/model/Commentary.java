package ru.otus.hw8SpringDataNoSQL.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    public Commentary() {
    }

    public Commentary(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commentary that = (Commentary) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Commentary{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
