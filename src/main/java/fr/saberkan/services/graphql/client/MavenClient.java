package fr.saberkan.services.graphql.client;

import java.util.Arrays;
import java.util.List;

import fr.saberkan.services.graphql.shemas.MavenSchema.Artifact;

/**
 * 
 * @author saberkan
 *
 */
public class MavenClient {

    /**
     * Cette méthode joue le role de recupération des données (DB, REST, etc.)
     * @param group
     * @param name
     * @return 
     */
    public static List<Artifact> findArtifacts(String group, String name) {
        // TODO LOGIC FILTRING
        return Arrays.asList(
                new Artifact("com.graphql.java", "graphql-java", "1.2"),
                new Artifact("com.graphql.java", "graphql-java", "1.3")
                );
    }
}
