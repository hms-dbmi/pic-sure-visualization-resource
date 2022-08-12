package edu.harvard.hms.dbmi.avillach.hpds.service;

import edu.harvard.hms.dbmi.avillach.hpds.model.domain.QueryRequest;

import java.util.Map;

public interface IHpdsService {

    Map<String, Map<String, Integer>> getCrossCountsMap(QueryRequest queryRequest);
}
