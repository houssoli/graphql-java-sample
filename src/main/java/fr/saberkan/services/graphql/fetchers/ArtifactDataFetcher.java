package fr.saberkan.services.graphql.fetchers;

import java.util.List;

import fr.saberkan.services.graphql.client.MavenClient;
import fr.saberkan.services.graphql.shemas.maven.Artifact;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class ArtifactDataFetcher implements DataFetcher {
    public static final String GROUP_ARGUMENT  ="group";
    public static final String NAME_ARGUMENT  ="name";
    
    public Object get(final DataFetchingEnvironment dataFetchingEnvironment) {
     final String name = (String) dataFetchingEnvironment.getArguments().get(NAME_ARGUMENT);
     final String group = (String) dataFetchingEnvironment.getArguments().get(GROUP_ARGUMENT);
     final List<Artifact> artifacts = MavenClient.findArtifacts(group, name);
     return artifacts;
    }

}