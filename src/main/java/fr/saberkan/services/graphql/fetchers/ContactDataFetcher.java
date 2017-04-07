package fr.saberkan.services.graphql.fetchers;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.saberkan.services.graphql.client.ContactClient;
import fr.saberkan.services.graphql.shemas.ContactSchema;
import fr.saberkan.services.graphql.shemas.contact.Person;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class ContactDataFetcher implements DataFetcher {
    private static final Logger LOG = LoggerFactory.getLogger(ContactDataFetcher.class);

    public Object get(final DataFetchingEnvironment dataFetchingEnvironment) {
        final String fname = (String) dataFetchingEnvironment.getArguments().get(ContactSchema.FNAME_ARGUMENT);
        final String skill = (String) dataFetchingEnvironment.getArguments().get(ContactSchema.SKILL_ARGUMENT);
        final List<Person> persons = ContactClient.findContacts(fname, skill);

        LOG.debug("persons => {}", persons);
        return persons;
    }
}