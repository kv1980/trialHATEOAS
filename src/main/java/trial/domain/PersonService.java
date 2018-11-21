package trial.domain;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PersonService {
    private List<Person> personList;

    public PersonService() {
        this.personList = new LinkedList<>();
        personList.add(new Person("Kristof","Vermeulen","TINB"));
        personList.add(new Person("Matthias","Depuydt","TINB"));
        personList.add(new Person("Sven","Bovens","TINB"));
        personList.add(new Person("Rik","Vanneste","TINB"));
        personList.add(new Person("Thomas","Janssenss","TINB"));
        personList.add(new Person("Francis","Debrabandere","TINB"));
        personList.add(new Person("Alain","Vandam","Synalco"));
    }

    public List<Person> findAll(){
        return personList;
    }

    public Optional<Person> findByUUID(UUID uuid){
        return personList.stream()
                .filter(person -> person.getUuid().equals(uuid))
                .findFirst();
    }

    public Optional<Person> findByName(String firstName, String lastName){
        return personList.stream()
                .filter(person -> person.getFirstName().equals(firstName)&&person.getLastName().equals(lastName))
                .findFirst();
    }
}
