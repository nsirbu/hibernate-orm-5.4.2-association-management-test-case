//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//
package org.hibernate.bugs;

import com.testcase.onetoone.model.OrderLine;
import com.testcase.onetoone.model.StockAllocation;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @since 05-Jun-2019
 * @author nsirbu
 */
public class OneToOneAssociationTest {

  @Test
  public void shouldSetTheOrderLineOnStockAllocation() {
    OrderLine orderLine = new OrderLine();
    StockAllocation stockAllocation = new StockAllocation();

    orderLine.setId("ol-ID");
    stockAllocation.setId("alloc-ID");

    orderLine.setStockAllocation(stockAllocation);

    Assert.assertNotNull("StockAllocation should have the OrderLine set after one side of the association has been set.",
            stockAllocation.getOrderLine());
  }

  @Test
  public void shouldUnSetTheOrderLineOnStockAllocation() {
    OrderLine orderLine = new OrderLine();
    StockAllocation stockAllocation = new StockAllocation();

    orderLine.setId("ol-ID");
    stockAllocation.setId("alloc-ID");

    orderLine.setStockAllocation(stockAllocation);

    orderLine.setStockAllocation(null);
    Assert.assertNull("StockAllocation should not have the OrderLine set after one side of the association has been unset.",
            stockAllocation.getOrderLine());
  }
}
