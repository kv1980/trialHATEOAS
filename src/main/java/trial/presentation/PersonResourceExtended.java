package trial.presentation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PersonResourceExtended extends PersonResourceConcise {
    private String team;

    @JsonCreator
    public PersonResourceExtended(UUID uuid, String firstName, String lastName, String team) {
        super(uuid,firstName,lastName);
        this.team = team;
    }

    public String getTeam() {
        return team;
    }
}