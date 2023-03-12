package com.zljtt.wiki.domain;

public class Card {
    private String setCode;

    private String collectorNumber;

    private Boolean foil;

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public String getCollectorNumber() {
        return collectorNumber;
    }

    public void setCollectorNumber(String collectorNumber) {
        this.collectorNumber = collectorNumber;
    }

    public Boolean getFoil() {
        return foil;
    }

    public void setFoil(Boolean foil) {
        this.foil = foil;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", setCode=").append(setCode);
        sb.append(", collectorNumber=").append(collectorNumber);
        sb.append(", foil=").append(foil);
        sb.append("]");
        return sb.toString();
    }
}