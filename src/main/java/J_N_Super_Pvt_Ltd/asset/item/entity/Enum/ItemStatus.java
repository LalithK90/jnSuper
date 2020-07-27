package J_N_Super_Pvt_Ltd.asset.item.entity.Enum;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {
    AVAILABLE("Available"),
    NOT_AVAILABLE("Not Available"),
    ROP("Need to order"),
    ORDERED("Ordered"),
    STOP("Not For Further");

    private final String itemStatus;
}

