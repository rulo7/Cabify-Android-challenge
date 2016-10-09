package com.racobos.data.mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rulo7 on 10/10/2016.
 */

public abstract class Mapper<E, M> {
    public abstract E mapModelToEntity(M model);

    public abstract M mapEntityToModel(E entity);

    public List<E> mapModelToEntity(List<M> models) {
        if (models != null) {
            List<E> list = new ArrayList<>();
            for (M model : models) {
                list.add(mapModelToEntity(model));
            }
            return list;
        }
        return null;
    }

    public List<M> mapEntityToModel(List<E> entities) {
        if (entities != null) {
            List<M> list = new ArrayList<>();
            for (E entity : entities) {
                list.add(mapEntityToModel(entity));
            }
            return list;
        }
        return null;
    }
}
