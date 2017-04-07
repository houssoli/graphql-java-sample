package fr.saberkan.services.graphql.shemas.maven;

/**
 * Created by houssoli on 08/04/17.
 */
public class Artifact {
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
