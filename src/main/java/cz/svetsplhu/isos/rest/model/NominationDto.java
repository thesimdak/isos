package cz.svetsplhu.isos.rest.model;

/**
 * Rest model for a nomination to a national championship.
 */
public class NominationDto {

    private String competitionName;
    private String firstName;
    private String lastName;
    private String yearOfBirth;
    private String time;

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
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

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Nomination{" +
                "competitionName='" + competitionName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
