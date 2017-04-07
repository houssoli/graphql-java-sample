package fr.saberkan.services.graphql.shemas;

import static graphql.schema.GraphQLObjectType.newObject;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

public class SingleSchema {

    private final GraphQLSchema schema;

    public SingleSchema() {
        final GraphQLFieldDefinition allArtifacts = MavenSchema.createQueryAllArtifacts();
        final GraphQLFieldDefinition fieldDefinition = ContactSchema.allContacts();

        final GraphQLObjectType rootQuery = newObject().name("rootQuery").field(allArtifacts).field(fieldDefinition).build();
        schema = GraphQLSchema.newSchema().query(rootQuery).build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }
}
