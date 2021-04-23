package lk.j_n_super_pvt_ltd.asset.report.model;

import lk.j_n_super_pvt_ltd.asset.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemSellPriceQuantityBuyingPrice {
private Item item;
private BigDecimal sellPrice;
private BigDecimal sellTotalPrice;
private long itemCounter;
private BigDecimal buyingPrice;
private BigDecimal buyingTotalPrice;


}
