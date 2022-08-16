package edu.harvard.hms.dbmi.avillach.visualization.model;

import edu.harvard.hms.dbmi.avillach.visualization.model.domain.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/pic-sure")
@Produces({"application/json"})
@Consumes({"application/json"})
public interface IResourceRS {

    @POST
    @Path("/info")
    default ResourceInfo info(QueryRequest queryRequest) {
        throw new NotSupportedException();
    }

    @POST
    @Path("/query")
    default QueryStatus query(QueryRequest queryJson) {
        throw new NotSupportedException();
    }

    @POST
    @Path("/query/{resourceQueryId}/status")
    default QueryStatus queryStatus(String queryId, QueryRequest statusRequest) {
        throw new NotSupportedException();
    }

    @POST
    @Path("/query/{resourceQueryId}/result")
    default Response queryResult(String queryId, QueryRequest resultRequest) {
        throw new NotSupportedException();
    }

    @POST
    @Path("/query/sync")
    default Response querySync(QueryRequest resultRequest) {
        throw new NotSupportedException("Query Sync is not implemented in this resource.  Please use query");
    }

    @POST
    @Path("/query/format")
    default Response queryFormat(QueryRequest resultRequest) {
        throw new NotSupportedException("Query formatting is not implemented in this resource.");
    }
}