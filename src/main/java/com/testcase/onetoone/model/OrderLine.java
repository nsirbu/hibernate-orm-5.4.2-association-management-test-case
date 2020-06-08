package com.testcase.onetoone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERLINE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderLine {

  @Column(nullable = false)
  private String id;

  @OneToOne(mappedBy = "orderLine", fetch = FetchType.LAZY, optional = true)
  private StockAllocation stockAllocation;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public StockAllocation getStockAllocation() {
    return stockAllocation;
  }

  public void setStockAllocation(StockAllocation stockAllocation) {
    this.stockAllocation = stockAllocation;
  }

  @Override
  public String toString() {
    return "OrderLine{" + "id=" + id + ", stockAllocation=" + stockAllocation + '}';
  }
}
