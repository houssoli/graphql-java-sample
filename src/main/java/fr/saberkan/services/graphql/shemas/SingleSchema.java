package fr.saberkan.services.graphql.shemas;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import static graphql.schema.GraphQLObjectType.newObject;

public class SingleSchema {

    private final GraphQLSchema schema;

    public SingleSchema() {
        // Query fields
        final GraphQLFieldDefinition allArtifacts = MavenSchema.createQueryAllArtifacts();
        final GraphQLFieldDefinition allContacts = ContactSchema.createQueryAllContacts();

        // Mutation fields
        final GraphQLFieldDefinition artifactMutation = MavenSchema.createArtifactMutation();

        final GraphQLObjectType rootQuery = newObject().name("rootQuery").field(allArtifacts).field(allContacts).build();
        final GraphQLObjectType rootMutation = newObject().name("rootMutation").field(artifactMutation).build();
        schema = GraphQLSchema.newSchema().query(rootQuery).mutation(rootMutation).build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }
}
