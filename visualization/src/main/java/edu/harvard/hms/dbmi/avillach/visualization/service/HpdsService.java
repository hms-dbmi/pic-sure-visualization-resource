package edu.harvard.hms.dbmi.avillach.visualization.service;

import edu.harvard.hms.dbmi.avillach.visualization.model.domain.QueryRequest;
import edu.harvard.hms.dbmi.avillach.visualization.model.domain.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class HpdsService implements IHpdsService {

    private Logger logger = LoggerFactory.getLogger(HpdsService.class);

    private static final String AUTH_HEADER_NAME = "Authorization";

    @Value("${picSure.url}")
    private String picSureUrl;
    @Value("${picSure.uuid}")
    private UUID picSureUuid;

    private RestTemplate restTemplate;

    public HpdsService() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
    }

    /**
     * Takes the current query and creates a CONTINUOUS_CROSS_COUNT query and sends that to HPDS.
     *
     * @param queryRequest - {@link QueryRequest} - contains the query filters to be sent to HPDS
     * @return List<ContinuousData> - A LinkedHashMap of the cross counts for category or continuous
     * date range and their respective counts
     */
    public Map<String, Map<String, Integer>> getCrossCountsMap(QueryRequest queryRequest, ResultType resultType) {
        try {
            sanityCheck(queryRequest, resultType);
            HttpHeaders headers = prepareQueryRequest(queryRequest, resultType);
            return restTemplate.exchange(picSureUrl, HttpMethod.POST, new HttpEntity<>(queryRequest, headers), LinkedHashMap.class).getBody();
        } catch (Exception e) {
            logger.error("Error getting cross counts: " + e.getMessage());
            e.printStackTrace();
            return new LinkedHashMap<>();
        }
    }

    /**
     * Create a new HttpHeaders object with the passed in queryRequest and ResultType. Transfers the authorization token
     * from the queryRequest and creates a new authorization header. Adds the resultType to the queryRequest.
     * Sets the correct Resource UUID
     * @param queryRequest - {@link QueryRequest} - contains the auth header
     * @param resultType - {@link ResultType} - determines the type of query to be sent to HPDS
     * @return HttpHeaders - the headers to be sent to HPDS
     */
    private HttpHeaders prepareQueryRequest(QueryRequest queryRequest, ResultType resultType) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTH_HEADER_NAME,
                queryRequest.getResourceCredentials().get(AUTH_HEADER_NAME)
        );

        queryRequest.getQuery().expectedResultType = resultType;
        queryRequest.setResourceUUID(picSureUuid);
        return headers;
    }

    private void sanityCheck(QueryRequest queryRequest, ResultType requestType) {
        if (picSureUrl == null) throw new IllegalArgumentException("picSureUrl is required");
        if (picSureUuid == null) throw new IllegalArgumentException("picSureUuid is required");
        if (queryRequest.getResourceCredentials().get(AUTH_HEADER_NAME) == null)
            throw new IllegalArgumentException("No authorization token found in queryRequest");
        if (requestType == null) throw new IllegalArgumentException("ResultType is required");
        if (requestType != ResultType.CATEGORICAL_CROSS_COUNT && requestType != ResultType.CONTINUOUS_CROSS_COUNT)
            throw new IllegalArgumentException("ResultType must be CATEGORICAL_CROSS_COUNT or CONTINUOUS_CROSS_COUNT");
    }
}
