package by.epam.training.external.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int id;

    @Column(name = "flight_number")
    @Expose
    private int flightNumber;

    @Column(name = "start_point")
    @Expose
    private String startPoint = "";

    @Column(name = "destination_point")
    @Expose
    private String destinationPoint = "";

    @Column(name = "departure_date_time")
    @Expose
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_date_time")
    @Expose
    private LocalDateTime arrivalDateTime;

    @Column
    @Expose
    private String plane = "";

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "crew_id")
    @Expose
    private Crew crew;

    public Flight() {
    }

    public Flight(
            int flightNumber, String startPoint, String destinationPoint,
            LocalDateTime departureDateTime, LocalDateTime arrivalDateTime,
            String plane
    ) {
        this.flightNumber = flightNumber;
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.plane = plane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id:" + id +
                ", flightNumber:" + flightNumber +
                ", startPoint:'" + startPoint + '\'' +
                ", destinationPoint:'" + destinationPoint + '\'' +
                ", departureDateTime:" + departureDateTime +
                ", arrivalDateTime:" + arrivalDateTime +
                ", plane:'" + plane + '\'' +
                ", crew:" + crew +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
