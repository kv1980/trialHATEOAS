package trial.domain;

import java.util.UUID;

public class Person {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String team;

    public Person(String firstName, String lastName, String team) {
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTeam(){
        return team;
    }

    public boolean isRelatedTo(Person otherPerson){
        if (this.equals(otherPerson)) {
            return false;
        }
        return this.team.equals(otherPerson.team);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!firstName.equals(person.firstName)) return false;
        return lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}
