package fr.saberkan.services.graphql.shemas;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import fr.saberkan.services.graphql.fetchers.ArtifactDataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

public     class MavenSchema {
    public static final String GROUP_ARGUMENT  ="group";
    public static final String NAME_ARGUMENT  ="name";
    public final GraphQLObjectType QUERY_RESULT_TYPE  =createQueryResultType();
    
    private final   GraphQLSchema   schema;
    
    public MavenSchema() {
        final ArtifactDataFetcher dataFetcher   =new ArtifactDataFetcher();
        final GraphQLArgument   groupArg    =GraphQLArgument.newArgument().name(GROUP_ARGUMENT).type(GraphQLString).build();
        final GraphQLArgument   nameArg    =GraphQLArgument.newArgument().name(NAME_ARGUMENT).type(GraphQLString).build();
    
        final GraphQLFieldDefinition    allArtifacts    =GraphQLFieldDefinition.newFieldDefinition()
                .name("allArtifacts")
                .type(new GraphQLList(QUERY_RESULT_TYPE))
                .argument(groupArg)
                .argument(nameArg)
                .dataFetcher(dataFetcher)
                .build();
        
        final GraphQLObjectType rootQuery   =newObject().name("rootQuery").field(allArtifacts).build();
        schema  = GraphQLSchema.newSchema().query(rootQuery).build();
    }
    
    
    private GraphQLObjectType    createQueryResultType() {
        final   GraphQLFieldDefinition  groupField  =newFieldDefinition().name("group").type(GraphQLString).build();
        final   GraphQLFieldDefinition  nameField  =newFieldDefinition().name("name").type(GraphQLString).build();
        final   GraphQLFieldDefinition  versionField  =newFieldDefinition().name("version").type(GraphQLString).build();
        return newObject().name("queryResult")
                .field(groupField)
                .field(nameField)
                .field(versionField)
                .build();
    }

    public GraphQLSchema getSchema() {return schema;}
    
    public static class Artifact {
        private String group;
        private String name;
        private String version;
        
        
        
        public Artifact(String group, String name, String version) {
            super();
            this.group = group;
            this.name = name;
            this.version = version;
        }
        public String getGroup() {
         return group;
        }
        public void setGroup(String group) {
         this.group = group;
        }
        public String getName() {
         return name;
        }
        public void setName(String name) {
         this.name = name;
        }
        public String getVersion() {
         return version;
        }
        public void setVersion(String version) {
         this.version = version;
        }
       
    }

}
