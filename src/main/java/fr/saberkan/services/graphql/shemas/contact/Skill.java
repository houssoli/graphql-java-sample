package fr.saberkan.services.graphql.shemas.contact;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Created by houssoli on 08/04/17.
 */
public class Skill {
    private String skill;
    private String yearsOfExperience;

    public Skill(String skill, String yearsOfExperience) {
        super();
        this.skill = skill;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill that = (Skill) o;

        return Objects.equals(this.skill, that.skill) &&
                Objects.equals(this.yearsOfExperience, that.yearsOfExperience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill, yearsOfExperience);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("skill = " + skill)
                .add("yearsOfExperience = " + yearsOfExperience)
                .toString();
    }
}
