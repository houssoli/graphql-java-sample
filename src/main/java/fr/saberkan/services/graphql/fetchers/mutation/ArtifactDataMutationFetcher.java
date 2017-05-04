package fr.saberkan.services.graphql.fetchers.mutation;

import fr.saberkan.services.graphql.client.MavenClient;
import fr.saberkan.services.graphql.shemas.maven.Artifact;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

public class ArtifactDataMutationFetcher implements DataFetcher {
    public static final String GROUP_ARGUMENT  ="group";
    public static final String NAME_ARGUMENT  ="name";
    public static final String VERSION_ARGUMENT  ="version";
    
    public Object get(final DataFetchingEnvironment dataFetchingEnvironment) {
     final String name = (String) dataFetchingEnvironment.getArguments().get(NAME_ARGUMENT);
     final String group = (String) dataFetchingEnvironment.getArguments().get(GROUP_ARGUMENT);
     final String version = (String) dataFetchingEnvironment.getArguments().get(VERSION_ARGUMENT);
     final List<Artifact> artifacts = MavenClient.createArtifact(group, name, version);
     return artifacts;
    }

}