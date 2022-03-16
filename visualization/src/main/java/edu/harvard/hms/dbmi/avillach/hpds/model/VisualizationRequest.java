package edu.harvard.hms.dbmi.avillach.hpds.model;

import edu.harvard.hms.dbmi.avillach.hpds.model.domain.QueryRequest;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class VisualizationRequest {
    private List<ContinuousData> continuousData;
    private List<CategoricalData> categoricalData;

    public List<ContinuousData> getContinuousData() {
        if (continuousData == null) {
            continuousData = new ArrayList<>();
        }
        return continuousData;
    }

    public List<CategoricalData> getCategoricalData() {
        if (categoricalData == null) {
            categoricalData = new ArrayList<>();
        }
        return categoricalData;
    }
}
