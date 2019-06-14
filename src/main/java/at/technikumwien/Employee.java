package at.technikumwien;

import lombok.*;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Data // erstellt Getter, Setter, Konstruktor, toString Methode, ...
@NoArgsConstructor // leerer Konstruktor
@AllArgsConstructor // Konstruktor mit allen Argumenten
@RequiredArgsConstructor // packt alle NonNull Properties in den Konstruktor
@Entity // es soll eine Tabelle erzeugt werden
@Table(name = "t_employee")
@NamedQuery(
        name = "Employee.selectAll", // könnte jeder Name sein. "Entity.Funktion" ist nur eine Konvention
        query = "SELECT e FROM Employee e ORDER BY e.lastName ASC, e.firstName ASC"
)
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // damit die ID eine eindeutige Sequenz ist
    private Long id;

    @NonNull
    @Column(length = 50, nullable = false)
    @JsonbProperty(value = "first-name", nillable = false)
    private String firstName;

    @NonNull
    @Column(length = 50, nullable = false)
    @JsonbProperty(value = "last-name", nillable = false)
    private String lastName;

    @NonNull
    @Enumerated(EnumType.STRING) // kann als String ("FEMALE, MALE") oder als Index gespeichert werden ("EnumType.ORDINAL")
    @Column(nullable = false)
    @JsonbProperty(nillable = false)
    private Sex sex;

    @NonNull
    @Column(nullable = false)
    @JsonbProperty(value = "birth-date", nillable = false)
    private LocalDate birthDate;

    @NonNull
    @Column(nullable = false)
    private boolean active;

    // required for unit testing only
    @Transient
    private Clock clock = Clock.systemDefaultZone();

    @JsonbTransient
    public String getDescription() {
        return getFullName() + " (" + getAgeInYears() + " Jahre, " + (active ? "aktiv" : "inaktiv") + ")";
    }

    @JsonbTransient
    public String getFullName() {
        Objects.requireNonNull(sex, "sex must not be null");

        return (sex == Sex.FEMALE ? "Frau" : "Herr") + " " + firstName + " " + lastName;
    }

    @JsonbTransient
    public int getAgeInYears() {
        return Period.between(birthDate, LocalDate.now(clock)).getYears();
    }
}
