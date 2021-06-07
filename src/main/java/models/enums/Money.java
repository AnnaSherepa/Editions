package models.enums;

public enum Money {
    UAH("uah"),
    USD("usd");

    private String measurement;

    Money(String measurement){
        this.measurement = measurement;
    }

    public String getMeasurement() {
        return measurement;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
