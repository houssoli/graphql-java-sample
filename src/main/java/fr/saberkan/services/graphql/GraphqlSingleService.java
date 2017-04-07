package fr.saberkan.services.graphql;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.saberkan.services.graphql.shemas.SingleSchema;
import graphql.ExecutionResult;
import graphql.GraphQL;


/**
 * @author saberkan
 */
@RestController
public class GraphqlSingleService {
    private static final Logger LOG = LoggerFactory.getLogger(GraphqlSingleService.class);
    private final GraphQL graphql;

    public GraphqlSingleService() {
        // Prepare graphql
        this.graphql = new GraphQL(new SingleSchema().getSchema());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/graphql/singleEndpoint")
    public Object singleService(@RequestBody(required = false) String query) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException {
        LOG.debug("query : " + query);

        // Execute
        final ExecutionResult executionResult = graphql.execute(query);

        final Object resultData = executionResult.getData();
        final Object resultErrors = executionResult.getErrors();
        LOG.debug("resultData  :" + resultData);
        LOG.debug("resultErrors  :" + resultErrors);
        return resultData == null ? resultErrors : resultData;
    }

}
