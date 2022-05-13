package sk.stuba.fei.uim.vsa.pr2.web.response;

import java.util.List;

public class UserWithCarsDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Long> cars;

    public UserWithCarsDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getCars() {
        return cars;
    }

    public void setCars(List<Long> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "UserWithCarsDto{" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", cars=" + cars +
                '}';
    }
}
