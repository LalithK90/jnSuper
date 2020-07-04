package J_N_Super_Pvt_Ltd.asset.invoice.entity.Enum;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    CASH("Cash"),
    CREDIT("Credit card"),
    CHEQUE("Cheque");
    private final String paymentMethod;
}
