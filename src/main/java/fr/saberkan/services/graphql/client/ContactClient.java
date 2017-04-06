package fr.saberkan.services.graphql.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.saberkan.services.graphql.shemas.ContactSchema.Person;
import fr.saberkan.services.graphql.shemas.ContactSchema.Skill;

public class ContactClient {
    private static List<Person>  persons   =Arrays.asList(
            new Person("FNAME1", "LNAME1", Arrays.asList(new Skill("java", "5"))),
            new Person("FNAME2", "LNAME2", Arrays.asList(new Skill("java", "5")))
            );

    /**
     * Cette méthode joue le role de recupération des données (DB, REST, etc.)
     * @param group
     * @param name
     * @return 
     */
    public static List<Person> findContacts(String fName, String skill) {
        List<Person>    result  =new ArrayList<Person>();
        persons.stream()
            .filter(x -> x.getfName().contains(fName))
            .forEach(x -> {
                for(Skill skillObject : x.getSkills()) {
                    if(skillObject.getSkill().contains(skill)) {
                        result.add(x);
                    }
                }
            });
        
        return result;
    }
}
