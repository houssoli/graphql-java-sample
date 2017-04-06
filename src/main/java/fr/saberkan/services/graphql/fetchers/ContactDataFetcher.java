package fr.saberkan.services.graphql.fetchers;

import fr.saberkan.services.graphql.client.ContactClient;
import fr.saberkan.services.graphql.shemas.ContactSchema;
import fr.saberkan.services.graphql.shemas.ContactSchema.Person;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

public class ContactDataFetcher implements DataFetcher {
    
    public Object get(final DataFetchingEnvironment dataFetchingEnvironment) {
     final String fname = (String) dataFetchingEnvironment.getArguments().get(ContactSchema.FNAME_ARGUMENT);
     final String skill = (String) dataFetchingEnvironment.getArguments().get(ContactSchema.SKILL_ARGUMENT);
     final List<Person> artifacts = ContactClient.findContacts(fname, skill);
     return artifacts;
    }
}