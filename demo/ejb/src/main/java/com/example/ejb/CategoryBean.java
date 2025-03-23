package com.example.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Stateless
public class CategoryBean {
    private List<Category> categories = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void init() {
        // Initialize categories with some fake data
        addCategory(new Category("Fiction"));
        addCategory(new Category("Science Fiction"));
        addCategory(new Category("Classic"));
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        category.setId(idGenerator.getAndIncrement());
        categories.add(category);
    }

    public Category findCategoryById(Long id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}