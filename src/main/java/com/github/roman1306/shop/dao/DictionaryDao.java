package com.github.roman1306.shop.dao;

import java.util.List;

public interface DictionaryDao<D> {

    List<D> load();
}
