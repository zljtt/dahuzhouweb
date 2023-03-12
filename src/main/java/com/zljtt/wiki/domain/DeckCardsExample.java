package com.zljtt.wiki.domain;

import java.util.ArrayList;
import java.util.List;

public class DeckCardsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeckCardsExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDeckIdIsNull() {
            addCriterion("deck_id is null");
            return (Criteria) this;
        }

        public Criteria andDeckIdIsNotNull() {
            addCriterion("deck_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeckIdEqualTo(Long value) {
            addCriterion("deck_id =", value, "deckId");
            return (Criteria) this;
        }

        public Criteria andDeckIdNotEqualTo(Long value) {
            addCriterion("deck_id <>", value, "deckId");
            return (Criteria) this;
        }

        public Criteria andDeckIdGreaterThan(Long value) {
            addCriterion("deck_id >", value, "deckId");
            return (Criteria) this;
        }

        public Criteria andDeckIdGreaterThanOrEqualTo(Long value) {
            addCriterion("deck_id >=", value, "deckId");
            return (Criteria) this;
        }

        public Criteria andDeckIdLessThan(Long value) {
            addCriterion("deck_id <", value, "deckId");
            return (Criteria) this;
        }

        public Criteria andDeckIdLessThanOrEqualTo(Long value) {
            addCriterion("deck_id <=", value, "deckId");
            return (Criteria) this;
        }

        public Criteria andDeckIdIn(List<Long> values) {
            addCriterion("deck_id in", values, "deckId");
            return (Criteria) this;
        }

        public Criteria andDeckIdNotIn(List<Long> values) {
            addCriterion("deck_id not in", values, "deckId");
            return (Criteria) this;
        }

        public Criteria andDeckIdBetween(Long value1, Long value2) {
            addCriterion("deck_id between", value1, value2, "deckId");
            return (Criteria) this;
        }

        public Criteria andDeckIdNotBetween(Long value1, Long value2) {
            addCriterion("deck_id not between", value1, value2, "deckId");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeIsNull() {
            addCriterion("card_set_code is null");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeIsNotNull() {
            addCriterion("card_set_code is not null");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeEqualTo(String value) {
            addCriterion("card_set_code =", value, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeNotEqualTo(String value) {
            addCriterion("card_set_code <>", value, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeGreaterThan(String value) {
            addCriterion("card_set_code >", value, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeGreaterThanOrEqualTo(String value) {
            addCriterion("card_set_code >=", value, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeLessThan(String value) {
            addCriterion("card_set_code <", value, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeLessThanOrEqualTo(String value) {
            addCriterion("card_set_code <=", value, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeLike(String value) {
            addCriterion("card_set_code like", value, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeNotLike(String value) {
            addCriterion("card_set_code not like", value, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeIn(List<String> values) {
            addCriterion("card_set_code in", values, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeNotIn(List<String> values) {
            addCriterion("card_set_code not in", values, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeBetween(String value1, String value2) {
            addCriterion("card_set_code between", value1, value2, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardSetCodeNotBetween(String value1, String value2) {
            addCriterion("card_set_code not between", value1, value2, "cardSetCode");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberIsNull() {
            addCriterion("card_collector_number is null");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberIsNotNull() {
            addCriterion("card_collector_number is not null");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberEqualTo(String value) {
            addCriterion("card_collector_number =", value, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberNotEqualTo(String value) {
            addCriterion("card_collector_number <>", value, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberGreaterThan(String value) {
            addCriterion("card_collector_number >", value, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberGreaterThanOrEqualTo(String value) {
            addCriterion("card_collector_number >=", value, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberLessThan(String value) {
            addCriterion("card_collector_number <", value, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberLessThanOrEqualTo(String value) {
            addCriterion("card_collector_number <=", value, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberLike(String value) {
            addCriterion("card_collector_number like", value, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberNotLike(String value) {
            addCriterion("card_collector_number not like", value, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberIn(List<String> values) {
            addCriterion("card_collector_number in", values, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberNotIn(List<String> values) {
            addCriterion("card_collector_number not in", values, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberBetween(String value1, String value2) {
            addCriterion("card_collector_number between", value1, value2, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andCardCollectorNumberNotBetween(String value1, String value2) {
            addCriterion("card_collector_number not between", value1, value2, "cardCollectorNumber");
            return (Criteria) this;
        }

        public Criteria andFoilIsNull() {
            addCriterion("foil is null");
            return (Criteria) this;
        }

        public Criteria andFoilIsNotNull() {
            addCriterion("foil is not null");
            return (Criteria) this;
        }

        public Criteria andFoilEqualTo(Boolean value) {
            addCriterion("foil =", value, "foil");
            return (Criteria) this;
        }

        public Criteria andFoilNotEqualTo(Boolean value) {
            addCriterion("foil <>", value, "foil");
            return (Criteria) this;
        }

        public Criteria andFoilGreaterThan(Boolean value) {
            addCriterion("foil >", value, "foil");
            return (Criteria) this;
        }

        public Criteria andFoilGreaterThanOrEqualTo(Boolean value) {
            addCriterion("foil >=", value, "foil");
            return (Criteria) this;
        }

        public Criteria andFoilLessThan(Boolean value) {
            addCriterion("foil <", value, "foil");
            return (Criteria) this;
        }

        public Criteria andFoilLessThanOrEqualTo(Boolean value) {
            addCriterion("foil <=", value, "foil");
            return (Criteria) this;
        }

        public Criteria andFoilIn(List<Boolean> values) {
            addCriterion("foil in", values, "foil");
            return (Criteria) this;
        }

        public Criteria andFoilNotIn(List<Boolean> values) {
            addCriterion("foil not in", values, "foil");
            return (Criteria) this;
        }

        public Criteria andFoilBetween(Boolean value1, Boolean value2) {
            addCriterion("foil between", value1, value2, "foil");
            return (Criteria) this;
        }

        public Criteria andFoilNotBetween(Boolean value1, Boolean value2) {
            addCriterion("foil not between", value1, value2, "foil");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}