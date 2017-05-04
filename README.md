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
query {
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

# EXEMPLE MUTATION artifact
<pre>
> METHODE : POST
> URL : http://localhost:8089/graphql/singleEndpoint
> BODY :
mutation {
   artifactMutation(group: "testGroupe", name: "testName", version: "1.0") {
   		group
   		name
   		version
   }
}
</pre>

# EXEMPLE FRAGMENT
<pre>
> METHODE : POST
> URL : http://localhost:8089/graphql/singleEndpoint
> BODY :
query {
    allArtifacts(group: "", name: "") {
		...fragmentMaven
    }
    contacts(firstName: "FNAME" skill: "java") {
        ...fragmentContact
    }
}
fragment fragmentContact on contactQueryResult {
    lastName
    skills {
        skill
    }
}
fragment fragmentMaven on mavenQueryResult {
    group
}
</pre>