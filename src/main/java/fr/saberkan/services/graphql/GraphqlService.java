package fr.saberkan.services.graphql;



import fr.saberkan.services.graphql.shemas.ContactSchema;
import fr.saberkan.services.graphql.shemas.MavenSchema;
import graphql.ExecutionResult;
import graphql.GraphQL;







import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author saberkan
 *
 */
@RestController
public class GraphqlService {
    private static  final   Logger      LOG     =LoggerFactory.getLogger(GraphqlService.class);


    @RequestMapping(method = RequestMethod.POST, value="/graphql/maven")
    public Object maven(@RequestBody(required=false) String query) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException {
        LOG.debug("query : " + query);

        // Prepare
        final MavenSchema schema = new MavenSchema();
        final GraphQL graphql = new GraphQL(schema.getSchema());
        final ExecutionResult executionResult = graphql.execute(query);

        // Execute
        final Object resultData = executionResult.getData();
        final Object resultErrors = executionResult.getErrors();
        LOG.debug("resultData  :" + resultData);
        LOG.debug("resultErrors  :" + resultErrors);
        return resultData==null?resultErrors:resultData;
    }

    @RequestMapping(method = RequestMethod.POST, value="/graphql/contact")
    public Object contact(@RequestBody(required=false) String query) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException {
        LOG.debug("query : " + query);

        // Prepare
        final ContactSchema schema = new ContactSchema();
        final GraphQL graphql = new GraphQL(schema.getSchema());
        final ExecutionResult executionResult = graphql.execute(query);

        // Execute
        final Object resultData = executionResult.getData();
        final Object resultErrors = executionResult.getErrors();
        LOG.debug("resultData  :" + resultData);
        LOG.debug("resultErrors  :" + resultErrors);
        return resultData==null?resultErrors:resultData;
    }
    
}
