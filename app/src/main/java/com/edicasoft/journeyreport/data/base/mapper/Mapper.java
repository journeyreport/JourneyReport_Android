package com.edicasoft.journeyreport.data.base.mapper;

import java.util.ArrayList;
import java.util.List;

public interface Mapper<E, M> {
    M map(final E e);

    default List<M> map(List<E> entities) {
        final List<M> result = new ArrayList<>(entities.size());
        for (final E e : entities) {
            result.add(map(e));
        }
        return result;
    }
}