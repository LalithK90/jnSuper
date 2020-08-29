package J_N_Super_Pvt_Ltd.asset.invoice.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoiceValidOrNot {
    VALID("Valid"),
    NOTVALID("No Valid");
    private final String invoiceValidOrNot;
}
