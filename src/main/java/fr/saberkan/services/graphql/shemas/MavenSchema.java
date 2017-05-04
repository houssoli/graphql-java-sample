package fr.saberkan.services.graphql.shemas;

import fr.saberkan.services.graphql.fetchers.ArtifactDataFetcher;
import fr.saberkan.services.graphql.fetchers.mutation.ArtifactDataMutationFetcher;
import graphql.schema.*;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class MavenSchema {
    public static final String GROUP_ARGUMENT = "group";
    public static final String NAME_ARGUMENT = "name";
    public static final String VERSION_ARGUMENT = "version";

    private final GraphQLSchema schema;

    public MavenSchema() {
        final GraphQLFieldDefinition allArtifacts = createQueryAllArtifacts();
        final GraphQLObjectType rootQuery = newObject().name("rootQuery").field(allArtifacts).build();
        schema = GraphQLSchema.newSchema().query(rootQuery).build();
    }

    static GraphQLFieldDefinition createQueryAllArtifacts() {
        final ArtifactDataFetcher dataFetcher = new ArtifactDataFetcher();
        final GraphQLArgument groupArg = GraphQLArgument.newArgument().name(GROUP_ARGUMENT).type(GraphQLString).build();
        final GraphQLArgument nameArg = GraphQLArgument.newArgument().name(NAME_ARGUMENT).type(GraphQLString).build();

        return GraphQLFieldDefinition.newFieldDefinition()
                .name("allArtifacts")
                .type(new GraphQLList(createQueryResultType()))
                .argument(groupArg)
                .argument(nameArg)
                .dataFetcher(dataFetcher)
                .build();
    }

    static GraphQLFieldDefinition createArtifactMutation() {
        final ArtifactDataMutationFetcher dataMutationFetcher = new ArtifactDataMutationFetcher();
        final GraphQLArgument groupArg = GraphQLArgument.newArgument().name(GROUP_ARGUMENT).type(GraphQLString).build();
        final GraphQLArgument nameArg = GraphQLArgument.newArgument().name(NAME_ARGUMENT).type(GraphQLString).build();
        final GraphQLArgument versionArg = GraphQLArgument.newArgument().name(VERSION_ARGUMENT).type(GraphQLString).build();

        return GraphQLFieldDefinition.newFieldDefinition()
                .name("artifactMutation")
                .type(new GraphQLList(createQueryResultType()))
                .argument(groupArg)
                .argument(nameArg)
                .argument(versionArg)
                .dataFetcher(dataMutationFetcher)
                .build();
    }

    static GraphQLObjectType createQueryResultType() {
        final GraphQLFieldDefinition groupField = newFieldDefinition().name("group").type(GraphQLString).build();
        final GraphQLFieldDefinition nameField = newFieldDefinition().name("name").type(GraphQLString).build();
        final GraphQLFieldDefinition versionField = newFieldDefinition().name("version").type(GraphQLString).build();
        return newObject().name("mavenQueryResult")
                .field(groupField)
                .field(nameField)
                .field(versionField)
                .build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }
}
