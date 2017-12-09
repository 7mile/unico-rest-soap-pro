package com.unico.dao;

import com.unico.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaofei on 2017/12/9.
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
