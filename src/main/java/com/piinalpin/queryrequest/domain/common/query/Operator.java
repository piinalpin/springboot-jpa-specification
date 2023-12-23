package com.piinalpin.queryrequest.domain.common.query;

import com.piinalpin.queryrequest.Exception.InvalidDataTypeException;
import com.piinalpin.queryrequest.Exception.KeyNotFoundException;
import lombok.extern.slf4j.Slf4j;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Path;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public enum Operator {

    EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = parseValue(request,false);
            Expression<?> key = this.getPath(root, request);
            return cb.and(cb.equal(key, value), predicate);
        }
    },

    NOT_EQUAL {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = parseValue(request,false);
            Expression<?> key = this.getPath(root, request);
            return cb.and(cb.notEqual(key, value), predicate);
        }
    },

    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Expression<String> key = this.getPath(root, request);
            return cb.and(cb.like(cb.upper(key), "%" +  parseValue(request,true) + "%"), predicate);
        }
    },

    IN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            List<Object> values = request.getValues();
            CriteriaBuilder.In<Object> inClause = cb.in(this.getPath(root, request));
            for (Object value : values) {
                inClause.value(parseValue(request,false));
            }
            return cb.and(inClause, predicate);
        }
    },

    BETWEEN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
            Object value = parseValue(request,false);
            Object valueTo = parseValueTo(request);
            if (request.getFieldType() == FieldType.DATE) {
                LocalDateTime startDate = (LocalDateTime) value;
                LocalDateTime endDate = (LocalDateTime) valueTo;
                Expression<LocalDateTime> key = this.getPath(root, request);
                return cb.and(cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)), predicate);
            }

            if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
                Number start = (Number) value;
                Number end = (Number) valueTo;
                Expression<Number> key = this.getPath(root, request);
                return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
            }

            log.info("Can not use between for {} field type.", request.getFieldType());
            return predicate;
        }
    };

    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate);

    public <T,V> Path<V> getPath(Root<T> root, FilterRequest request) {
        try {
            return root.get(request.getKey());
        }catch (Exception e){
            throw new KeyNotFoundException(request.getKey());
        }
    }

    public Object parseValue(FilterRequest request ,boolean upperCase){
        try {
            if (upperCase)
                return request.getFieldType().parse(request.getValue().toString().toUpperCase());
            else
                return request.getFieldType().parse(request.getValue().toString());
        }catch (Exception e){
            throw new InvalidDataTypeException();
        }
    }

    public Object parseValueTo(FilterRequest request){
        try {
            return request.getFieldType().parse(request.getValueTo().toString());
        }catch (Exception e){
            throw new InvalidDataTypeException();
        }
    }

}
