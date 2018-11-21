package trial.presentation;

import org.springframework.stereotype.Component;
import trial.domain.Person;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PersonResourceAssembler {

    public PersonResourceExtended toExtendedResource(Person person) {
        return person == null ? null : new PersonResourceExtended(person.getUuid(),person.getFirstName(),person.getLastName(),person.getTeam());
    }

    public PersonResourceConcise toConciseResource(Person person, List<Person> personList) {
        PersonResourceConcise personResourceConcise = person == null ? null : new PersonResourceConcise(person.getUuid(),person.getFirstName(),person.getLastName());
        personResourceConcise.add(linkTo(methodOn(PersonController.class).getOneByUuid(person.getUuid())).withSelfRel());
        personList.stream()
                  .filter(personFromList -> personFromList.isRelatedTo(person))
                  .forEach(relatedPerson ->
                          personResourceConcise.add(linkTo(methodOn(PersonController.class).getOneByUuid(relatedPerson.getUuid())).withRel("related")));
        return personResourceConcise;
    }
}

