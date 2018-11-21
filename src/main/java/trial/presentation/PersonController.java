package trial.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trial.domain.Person;
import trial.domain.PersonService;
import trial.domain.exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService service;
    private final PersonResourceAssembler assembler;

    public PersonController(PersonService service, PersonResourceAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<List<PersonResourceConcise>> getAll(){
        final List<Person> persons = service.findAll();
        final List<PersonResourceConcise> personResources = new ArrayList<>();

        persons.forEach(person ->{
            PersonResourceConcise personResource = assembler.toConciseResource(person,persons);
            personResources.add(personResource);
        });

        return new ResponseEntity<List<PersonResourceConcise>>(personResources, HttpStatus.OK);
    }

    @GetMapping("{uuid}")
    public ResponseEntity<PersonResourceExtended> getOneByUuid(@PathVariable("uuid")UUID uuid){
        Person person = service.findByUUID(uuid).orElseThrow(PersonNotFoundException::new);
        PersonResourceExtended personResource = assembler.toExtendedResource(person);
        return new ResponseEntity<PersonResourceExtended>(personResource, HttpStatus.OK);
    }
}
