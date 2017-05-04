package fr.saberkan.services.graphql.client;

import fr.saberkan.services.graphql.shemas.maven.Artifact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author saberkan
 *
 */
public class MavenClient {
    private static final Logger LOG = LoggerFactory.getLogger(MavenClient.class);

    private static List<Artifact>  artifacts   =new ArrayList<Artifact>(Arrays.asList(
            new Artifact("com.graphql.java", "graphql-java", "1.2"),
            new Artifact("com.graphql.java", "graphql-java", "1.3"),
            new Artifact("com.apache.commons", "apache-commons", "5.0")
            ));

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

    /**
     * Cette méthode est utilisé pour mutatation, elle insére et retourne les données
     * @param group
     * @param name
     * @param version
     * @return
     */
    public static List<Artifact> createArtifact(String group, String name, String version) {
        LOG.debug("createArtifact({}, {}, {})", group, name, version);
        artifacts.add(new Artifact(group, name, version));
        return artifacts.stream()
                .filter(x -> x.getGroup().contains(group) && x.getName().contains(name) && x.getVersion().contains(version))
                .collect(Collectors.toList());
    }
}
