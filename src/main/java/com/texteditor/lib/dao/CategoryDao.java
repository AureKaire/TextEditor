package com.texteditor.lib.dao;

import com.texteditor.lib.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    void delete(Category category);
}
