package by.epam.training.external.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @Column
    @Expose
    private String name = "";

    @Column
    @Expose
    private String surname = "";

    @Column
    @Expose
    private Position position;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER)
    private Set<Crew> crews; // transient

    public Employee() {
        crews = new HashSet<>();
    }

    public Employee(String name, String surname, Position position) {
        this();
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Set<Crew> getCrews() {
        return crews;
    }

    public void setCrews(Set<Crew> crews) {
        this.crews = crews;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", surname:'" + surname + '\'' +
                ", position:" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Position of Employee.
     */
    public enum Position {
        PILOT,
        NAVIGATOR,
        COMMUNICATOR,
        STEWARDESS
    }
}
