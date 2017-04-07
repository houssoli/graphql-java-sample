package fr.saberkan.services.graphql.shemas;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import fr.saberkan.services.graphql.fetchers.ContactDataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

public class ContactSchema {
    public static final String FNAME_ARGUMENT = "firstName";
    public static final String SKILL_ARGUMENT = "skill";
    public final GraphQLObjectType QUERY_RESULT_TYPE = createQueryResultType();

    private final GraphQLSchema schema;

    public ContactSchema() {
        final ContactDataFetcher dataFetcher = new ContactDataFetcher();
        final GraphQLArgument firstNameArg = GraphQLArgument.newArgument().name(FNAME_ARGUMENT).type(GraphQLString).build();
        final GraphQLArgument skillArg = GraphQLArgument.newArgument().name(SKILL_ARGUMENT).type(GraphQLString).build();

        final GraphQLFieldDefinition contacts = newFieldDefinition()
                .name("contacts")
                .type(new GraphQLList(QUERY_RESULT_TYPE))
                .argument(firstNameArg)
                .argument(skillArg)
                .dataFetcher(dataFetcher)
                .build();

        final GraphQLObjectType rootQuery = newObject().name("rootQuery").field(contacts).build();
        schema = GraphQLSchema.newSchema().query(rootQuery).build();

    }

    private GraphQLObjectType createQueryResultType() {
        final GraphQLFieldDefinition skillField = newFieldDefinition().name("skill").type(GraphQLString).build();
        final GraphQLFieldDefinition yearsOfExperienceField = newFieldDefinition().name("yearsOfExperience").type(GraphQLString).build();
        GraphQLObjectType skill = newObject().name("skill")
                .field(skillField)
                .field(yearsOfExperienceField)
                .build();

        final GraphQLFieldDefinition firstNameField = newFieldDefinition().name("firstName").type(GraphQLString).build();
        final GraphQLFieldDefinition lNameField = newFieldDefinition().name("lastName").type(GraphQLString).build();
        final GraphQLFieldDefinition skills = newFieldDefinition()
                .name("skills")
                .type(new GraphQLList(skill))
                .build();
        return newObject().name("person")
                .field(firstNameField)
                .field(lNameField)
                .field(skills)
                .build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }

    public static class Person {
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

    public static class Skill {
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
}
