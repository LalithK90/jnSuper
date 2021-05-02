package lk.j_n_super_pvt_ltd.asset.invoice.entity.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    CASH("Cash"),
    CHEQUE("Cheque"),
    CREDIT("Credit card");
    private final String paymentMethod;
}
