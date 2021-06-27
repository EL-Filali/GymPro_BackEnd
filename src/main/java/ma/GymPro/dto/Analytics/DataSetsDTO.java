package ma.GymPro.dto.Analytics;

import lombok.Data;

import java.util.List;

@Data
public class DataSetsDTO {
    private List<String> label;
    private  List<Float> data;

}
