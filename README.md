##### The other side of an OneToOne association is not unset when one side invokes the setter with NULL as argument
https://hibernate.atlassian.net/browse/HHH-13425

##### Test case description

Here's an interesting situation I discovered with the OneToOne associations and the bidirectional association management. Suppose there are two entities: `StockAllocation` and `OrderLine`.

Inside `OrderLine` entity I have:
```
@OneToOne(mappedBy = "orderLine", fetch = FetchType.LAZY, optional = true)
private StockAllocation stockAllocation;
```

And inside `StockAllocation` entity I have:
```
@OneToOne(fetch = LAZY, optional = false)
private OrderLine orderLine;
```

Now after these 2 classes have been enhanced, suppose at some point I want to unset the `orderLine` from the `stockAllocation`, I do it in the following way:
```
orderLine.setStockAllocation(null);
```

This sets the `stockAllocation` to `null` on the `orderLine` entity, but is doesn’t set the `orderLine` to `null` on the `stockAllocation` entity. Wasn’t the bytecode enhancement supposed to do that?, by changing one side of a bi-directional relation, the other side gets updated as well?

If I look the the bytecode, I see the following:
```
public void setStockAllocation(StockAllocation stockAllocation) {
 $$_hibernate_write_stockAllocation(stockAllocation);
}

public void $$_hibernate_write_stockAllocation(StockAllocation paramStockAllocation) {
 if (this.stockAllocation != null && Hibernate.isPropertyInitialized(this.stockAllocation, "orderLine") && paramStockAllocation != null) {
  null;
  ((StockAllocation) this.stockAllocation).$$_hibernate_write_orderLine(null);
 }

 OrderLine orderLine = this;
 StockAllocation stockAllocation1 = paramStockAllocation;

 if (!Objects.deepEquals(stockAllocation1, orderLine.stockAllocation))
  orderLine.$$_hibernate_trackChange("stockAllocation");
 
 orderLine.stockAllocation = stockAllocation1;

 if (paramStockAllocation != null && Hibernate.isPropertyInitialized(paramStockAllocation, "orderLine") && ((StockAllocation) paramStockAllocation).$$_hibernate_read_orderLine() != this) {
  this;
  ((StockAllocation) paramStockAllocation).$$_hibernate_write_orderLine(this);
 }
}
```

The `stockAllocation` is indeed set to `null` (the argument of this method is `null`), but the `orderLine` from the `stockAllocation` isn’t set to `null`, because the first IF will be true only when the argument of the method will be not `null`.