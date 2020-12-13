package j_n_super_pvt_ltd.asset.common_asset.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LiveOrDead {
    ACTIVE("Active"),
    STOP("Stop");

    private final String liveOrDead;
}
