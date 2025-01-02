package iuh.fit.dhktpm117ctt.group06.entities.enums;

public enum ProductSize {
    SIZE_30("30"),
    SIZE_32("32"),
    SIZE_34("34"),
    SIZE_36("36"),
    SIZE_38("38"),
    SIZE_40("40"),
    SIZE_42("42"),
    SIZE_44("44"),
    SIZE_46("46"),
    SIZE_48("48"),
    SIZE_50("50"),
    SIZE_52("52");

    private String size;

    ProductSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
