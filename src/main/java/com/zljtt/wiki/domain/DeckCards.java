package com.zljtt.wiki.domain;

public class DeckCards {
    private Long deckId;

    private String cardSetCode;

    private String cardCollectorNumber;

    private Boolean foil;

    private Integer amount;

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }

    public String getCardSetCode() {
        return cardSetCode;
    }

    public void setCardSetCode(String cardSetCode) {
        this.cardSetCode = cardSetCode;
    }

    public String getCardCollectorNumber() {
        return cardCollectorNumber;
    }

    public void setCardCollectorNumber(String cardCollectorNumber) {
        this.cardCollectorNumber = cardCollectorNumber;
    }

    public Boolean getFoil() {
        return foil;
    }

    public void setFoil(Boolean foil) {
        this.foil = foil;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deckId=").append(deckId);
        sb.append(", cardSetCode=").append(cardSetCode);
        sb.append(", cardCollectorNumber=").append(cardCollectorNumber);
        sb.append(", foil=").append(foil);
        sb.append(", amount=").append(amount);
        sb.append("]");
        return sb.toString();
    }
}