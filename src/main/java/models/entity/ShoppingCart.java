package models.entity;

import manegers.ProjectConstants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart implements Serializable {
    private List<Edition> editions;
    private BigDecimal totalSum;

    public ShoppingCart(){
        editions = new LinkedList<>();
        totalSum = BigDecimal.valueOf(0);
    }

    public List<Edition> getEditions() {
        return editions;
    }

    public void setEditions(List<Edition> editions) {
        this.editions = editions;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }
    public boolean isEmpty(){
        return editions.isEmpty();
    }
    public void addEdition(Edition edition){
        editions.add(edition);
        BigDecimal price = edition.getPrice();
        if (edition.getMeasurement().equals("UAH")){
            price = price.divide(ProjectConstants.USD_TO_UAH, 6, RoundingMode.HALF_UP);
        }
        totalSum = totalSum.add(price);
        totalSum = totalSum.round(new MathContext(4, RoundingMode.HALF_UP));

    }

    public void increaseTotalSum(BigDecimal value){
        totalSum = totalSum.add(value);
    }

    public void decreaseTotalSum(BigDecimal value){
        totalSum = totalSum.subtract(value);
    }


    public void clear(){
        editions.clear();
        totalSum = BigDecimal.ZERO;
    }

    public boolean existInCart(int id){
        Edition checked = editions.stream().filter(edition1 -> id  == edition1.getId()).findAny().orElse(null);
        return checked != null;
    }


}
