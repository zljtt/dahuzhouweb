package com.zljtt.wiki.domain;

import java.util.ArrayList;
import java.util.List;

public class CardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardExample() {
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

        public Criteria andSetCodeIsNull() {
            addCriterion("set_code is null");
            return (Criteria) this;
        }

        public Criteria andSetCodeIsNotNull() {
            addCriterion("set_code is not null");
            return (Criteria) this;
        }

        public Criteria andSetCodeEqualTo(String value) {
            addCriterion("set_code =", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeNotEqualTo(String value) {
            addCriterion("set_code <>", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeGreaterThan(String value) {
            addCriterion("set_code >", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeGreaterThanOrEqualTo(String value) {
            addCriterion("set_code >=", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeLessThan(String value) {
            addCriterion("set_code <", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeLessThanOrEqualTo(String value) {
            addCriterion("set_code <=", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeLike(String value) {
            addCriterion("set_code like", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeNotLike(String value) {
            addCriterion("set_code not like", value, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeIn(List<String> values) {
            addCriterion("set_code in", values, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeNotIn(List<String> values) {
            addCriterion("set_code not in", values, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeBetween(String value1, String value2) {
            addCriterion("set_code between", value1, value2, "setCode");
            return (Criteria) this;
        }

        public Criteria andSetCodeNotBetween(String value1, String value2) {
            addCriterion("set_code not between", value1, value2, "setCode");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberIsNull() {
            addCriterion("collector_number is null");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberIsNotNull() {
            addCriterion("collector_number is not null");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberEqualTo(String value) {
            addCriterion("collector_number =", value, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberNotEqualTo(String value) {
            addCriterion("collector_number <>", value, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberGreaterThan(String value) {
            addCriterion("collector_number >", value, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberGreaterThanOrEqualTo(String value) {
            addCriterion("collector_number >=", value, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberLessThan(String value) {
            addCriterion("collector_number <", value, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberLessThanOrEqualTo(String value) {
            addCriterion("collector_number <=", value, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberLike(String value) {
            addCriterion("collector_number like", value, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberNotLike(String value) {
            addCriterion("collector_number not like", value, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberIn(List<String> values) {
            addCriterion("collector_number in", values, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberNotIn(List<String> values) {
            addCriterion("collector_number not in", values, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberBetween(String value1, String value2) {
            addCriterion("collector_number between", value1, value2, "collectorNumber");
            return (Criteria) this;
        }

        public Criteria andCollectorNumberNotBetween(String value1, String value2) {
            addCriterion("collector_number not between", value1, value2, "collectorNumber");
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