package lk.J_N_Super_Pvt_Ltd.asset.commonAsset.model.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CivilStatus {
    //SINGLE("Single"),
    MARRIED("Married"),
    UNMARRIED("UnMarried");

    private final String civilStatus;

    public class Single {
    }
}
