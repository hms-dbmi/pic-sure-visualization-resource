package edu.harvard.hms.dbmi.avillach.hpds.service;

import edu.harvard.hms.dbmi.avillach.hpds.model.CategoricalData;
import edu.harvard.hms.dbmi.avillach.hpds.model.ContinuousData;
import edu.harvard.hms.dbmi.avillach.hpds.model.domain.QueryRequest;

import java.util.List;
import java.util.Map;

public interface IDataService {

    List<CategoricalData> getCategoricalData(QueryRequest queryRequest, Map<String, Map<String, Integer>> crossCountsMap);

    List<ContinuousData> getContinuousData(QueryRequest queryRequest, Map<String, Map<String, Integer>> crossCountsMap);

}
