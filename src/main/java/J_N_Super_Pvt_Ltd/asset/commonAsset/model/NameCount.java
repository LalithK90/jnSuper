package J_N_Super_Pvt_Ltd.asset.commonAsset.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NameCount {
    private String name;
    private String nameOne;
    private Integer count;

    private List<ParameterCount> parameterCounts;
}
