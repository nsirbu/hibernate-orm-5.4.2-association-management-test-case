package com.testcase.onetoone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OLALLOC")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class StockAllocation {

  @Column(nullable = false)
  private String id;

  @OneToOne(fetch = LAZY, optional = false)
  private OrderLine orderLine;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public OrderLine getOrderLine() {
    return orderLine;
  }

  public void setOrderLine(OrderLine orderLine) {
    this.orderLine = orderLine;
  }

  @Override
  public String toString() {
    return "StockAllocation{" + "id=" + id + ", orderLine=" + orderLine + '}';
  }
}
