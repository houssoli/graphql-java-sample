package fr.saberkan.services.graphql.client;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.saberkan.services.graphql.shemas.maven.Artifact;

/**
 * 
 * @author saberkan
 *
 */
public class MavenClient {

    private static List<Artifact>  artifacts   =Arrays.asList(
            new Artifact("com.graphql.java", "graphql-java", "1.2"),
            new Artifact("com.graphql.java", "graphql-java", "1.3"),
            new Artifact("com.apache.commons", "apache-commons", "5.0")
            );

    /**
     * Cette méthode joue le role de recupération des données (DB, REST, etc.)
     * @param group
     * @param name
     * @return 
     */
    public static List<Artifact> findArtifacts(String group, String name) {
        return artifacts.stream()
            .filter(x -> x.getGroup().contains(group) && x.getName().contains(name))
            .collect(Collectors.toList());
    }
}
