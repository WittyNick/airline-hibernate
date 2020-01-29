package by.epam.training.external.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "crews")
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @Column
    @Expose
    private String name = "";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "crew_employee",
            joinColumns = @JoinColumn(name = "crew_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @Expose
    private Set<Employee> employees;

    @OneToOne(mappedBy = "crew")
    private Flight flight; // transient

    public Crew() {
        employees = new HashSet<>();
    }

    public Crew(String name) {
        this();
        this.name = name;
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", employees:" + employees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crew crew = (Crew) o;
        return id == crew.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
