package lk.J_N_Super_Pvt_Ltd.asset.invoice.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoiceState {
    PAID("Paid"),
    CANCELLED("Cancelled");

    private final String invoiceState;
}
