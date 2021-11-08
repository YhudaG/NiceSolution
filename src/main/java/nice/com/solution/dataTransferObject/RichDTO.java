package nice.com.solution.dataTransferObject;

import java.math.BigDecimal;

public class RichDTO {

    private long richId;
    private String firstName;
    private String lastName;
    private BigDecimal fortune;

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

    public BigDecimal getFortune() {
        return fortune;
    }

    public void setFortune(BigDecimal fortune) {
        this.fortune = fortune;
    }

}


