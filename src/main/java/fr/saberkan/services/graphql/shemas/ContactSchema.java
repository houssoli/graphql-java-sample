package fr.saberkan.services.graphql.shemas;

import fr.saberkan.services.graphql.fetchers.ContactDataFetcher;
import graphql.schema.*;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class ContactSchema {
    public static final String FNAME_ARGUMENT = "firstName";
    public static final String SKILL_ARGUMENT = "skill";

    private final GraphQLSchema schema;

    public ContactSchema() {
        final GraphQLObjectType rootQuery = newObject().name("rootQuery").field(createQueryAllContacts()).build();
        schema = GraphQLSchema.newSchema().query(rootQuery).build();
    }

    static GraphQLFieldDefinition createQueryAllContacts() {
        final ContactDataFetcher dataFetcher = new ContactDataFetcher();
        final GraphQLArgument firstNameArg = GraphQLArgument.newArgument().name(FNAME_ARGUMENT).type(GraphQLString).build();
        final GraphQLArgument skillArg = GraphQLArgument.newArgument().name(SKILL_ARGUMENT).type(GraphQLString).build();

        final GraphQLFieldDefinition contacts = newFieldDefinition()
                .name("contacts")
                .type(new GraphQLList(createQueryResultType()))
                .argument(firstNameArg)
                .argument(skillArg)
                .dataFetcher(dataFetcher)
                .build();

        return contacts;
    }

    static GraphQLObjectType createQueryResultType() {
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
        return newObject().name("contactQueryResult")
                .field(firstNameField)
                .field(lNameField)
                .field(skills)
                .build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }

}
