package nice.com.solution.model;

import javax.persistence.*;

@Entity
@Table(name = "riches")
public class Rich {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long richId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private double fortune;

    public long getRichId() {
        return richId;
    }

    public void setRichId(long richId) {
        this.richId = richId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getFortune() {
        return fortune;
    }

    public void setFortune(double fortune) {
        this.fortune = fortune;
    }




}
