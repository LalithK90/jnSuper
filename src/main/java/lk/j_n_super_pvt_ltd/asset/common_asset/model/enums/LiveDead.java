package lk.j_n_super_pvt_ltd.asset.common_asset.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LiveDead {
    ACTIVE("Active"),
    STOP("Stop");

    private final String liveDeath;
}
