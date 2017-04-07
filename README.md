# PRESENTATION
Ce Projet est une démo d'implémentation de graphQL en java.

Cette implémentation est inspiré de l'article : 
https://medium.com/@dp118m/a-6-legged-blue-frankenduck-or-how-to-build-a-graphql-compatible-api-in-java-ade1eb3b9436

# DEMARRAGE
<pre>
$ mvn spring-boot:run -Dserver.port=8089
</pre>

# EXEMPLE QUERY allArtifacts
<pre>
> METHODE : POST
> URL : http://localhost:8089/graphql/maven
> BODY :
{
    allArtifacts(group: "com.graphql.java", name: "graphql-java") {
        group
        name
        version
    }
}
</pre>

# EXEMPLE QUERY contacts
<pre>
> METHODE : POST
> URL : http://localhost:8089/graphql/contacts
> BODY :
{
    contacts(firstName: "FNAME" skill: "java") {
        firstName
        lastName
        skills {
            skill
            yearsOfExperience
        }
    }
}
</pre>

# EXEMPLE QUERIES allArtifacts / contacts
<pre>
> METHODE : POST
> URL : http://localhost:8089/graphql/singleEndpoint
> BODY :
{
    allArtifacts(group: "com.graphql.java", name: "graphql-java") {
        group
        name
        version
    }
    contacts(firstName: "FNAME" skill: "java") {
        firstName
        lastName
        skills {
            skill
            yearsOfExperience
        }
    }
}
</pre>