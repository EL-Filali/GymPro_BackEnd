package ma.GymPro.dto.Analytics;

import lombok.Data;

import java.util.List;

@Data
public class GraphDynamicDTO {
    private List<String> labels;
    private DataSetsDTO datasets;
}
