package fr.saberkan.services.graphql.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.saberkan.services.graphql.shemas.ContactSchema.Person;
import fr.saberkan.services.graphql.shemas.ContactSchema.Skill;

public class ContactClient {
    private static List<Person> persons = Arrays.asList(
            new Person("FNAME1", "LNAME1", Arrays.asList(new Skill("java", "5"))),
            new Person("FNAME2", "LNAME2", Arrays.asList(new Skill("java", "5")))
    );

    /**
     * Cette méthode joue le role de recupération des données (DB, REST, etc.)
     *
     * @param firstName
     * @param skill
     * @return
     */
    public static List<Person> findContacts(String firstName, String skill) {
        List<Person> result = new ArrayList<Person>();

/*        final List<Skill> skills = persons.stream()
                .filter(x -> x.getFirstName().contains(firstName))
                .flatMap(person -> person.getSkills().stream())
                .filter(sk -> sk.getSkill().contains(skill)).collect(Collectors.toList());*/

        result.addAll(persons.stream()
                .filter(p -> p.getFirstName().contains(firstName) && p.getSkills().stream().filter(s -> s.getSkill().contains(skill)).count() > 0).collect(Collectors.toList()));

        return result;
    }
}
