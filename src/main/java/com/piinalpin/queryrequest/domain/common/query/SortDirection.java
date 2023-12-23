package com.piinalpin.queryrequest.domain.common.query;

import com.piinalpin.queryrequest.Exception.KeyNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

public enum SortDirection {

    ASC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request) {
            return cb.asc(getPath(root,request));
        }
    },
    DESC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request) {
            return cb.desc(getPath(root,request));
        }
    };

    public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request);
    public <T,V> Path<V> getPath(Root<T> root, SortRequest request) {
        try {
            return root.get(request.getKey());
        }catch (Exception e){
            throw new KeyNotFoundException(request.getKey());
        }
    }

}
