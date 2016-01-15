package com.letsappit.mineautomation.Category;

/**
 * Created by Admin on 30-12-2015.
 */
public class Category {

    public Category(String code, CategoryType categoryType, String description) {
        this.code = code;
        this.categoryType = categoryType;
        Description = description;
    }

    public Category(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
    private String code;
    private String Description;
    private CategoryType categoryType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
