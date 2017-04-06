package fr.saberkan.services.graphql.shemas;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.util.List;

import fr.saberkan.services.graphql.fetchers.ArtifactDataFetcher;
import fr.saberkan.services.graphql.fetchers.ContactDataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

public class ContactSchema {
    public static final String FNAME_ARGUMENT  ="fName";
    public static final String SKILL_ARGUMENT  ="skill";
    public final GraphQLObjectType QUERY_RESULT_TYPE  =createQueryResultType();

    private final   GraphQLSchema   schema;    

    public ContactSchema() {
        final ContactDataFetcher dataFetcher   =new ContactDataFetcher();
        final GraphQLArgument   fNameArg    =GraphQLArgument.newArgument().name(FNAME_ARGUMENT).type(GraphQLString).build();
        final GraphQLArgument   skillArg    =GraphQLArgument.newArgument().name(SKILL_ARGUMENT).type(GraphQLString).build();
    
        final GraphQLFieldDefinition    allArtifacts    =GraphQLFieldDefinition.newFieldDefinition()
                .name("contacts")
                .type(new GraphQLList(QUERY_RESULT_TYPE))
                .argument(fNameArg)
                .argument(skillArg)
                .dataFetcher(dataFetcher)
                .build();
        
        final GraphQLObjectType rootQuery   =newObject().name("rootQuery").field(allArtifacts).build();
        schema  = GraphQLSchema.newSchema().query(rootQuery).build();

    }

    private GraphQLObjectType    createQueryResultType() {
        final   GraphQLFieldDefinition  skillField  =newFieldDefinition().name("skill").type(GraphQLString).build();
        final   GraphQLFieldDefinition  yearsOfExperienceField  =newFieldDefinition().name("yearsOfExperience").type(GraphQLString).build();
        GraphQLObjectType   skill   =newObject().name("skill")
                .field(skillField)
                .field(yearsOfExperienceField)
                .build();

        final   GraphQLFieldDefinition  fNameField  =newFieldDefinition().name("fName").type(GraphQLString).build();
        final   GraphQLFieldDefinition  lNameField  =newFieldDefinition().name("lName").type(GraphQLString).build();
        final   GraphQLFieldDefinition  skills  =newFieldDefinition().name("skills").type(new GraphQLList(skill)).build();
        return newObject().name("person")
                .field(fNameField)
                .field(lNameField)
                .field(skills)
                .build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }

    public static class Person {
        private String fName;
        private String lName;
        private List<Skill> skills;
        
        
        
        public Person(String fName, String lName, List<Skill> skills) {
            super();
            this.fName = fName;
            this.lName = lName;
            this.skills = skills;
        }
        public String getfName() {
            return fName;
        }
        public void setfName(String fName) {
            this.fName = fName;
        }
        public String getlName() {
            return lName;
        }
        public void setlName(String lName) {
            this.lName = lName;
        }
        public List<Skill> getSkills() {
            return skills;
        }
        public void setSkills(List<Skill> skills) {
            this.skills = skills;
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
        
        
    }
}
