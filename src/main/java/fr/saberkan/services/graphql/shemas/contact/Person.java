package fr.saberkan.services.graphql.shemas.contact;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by houssoli on 08/04/17.
 */
public class Person {
    private String firstName;
    private String lastName;
    private List<Skill> skills;

    public Person(String firstName, String lastName, List<Skill> skills) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person that = (Person) o;

        return Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.skills, that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, skills);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("firstName = " + firstName)
                .add("lastName = " + lastName)
                .add("skills = " + skills)
                .toString();
    }
}
