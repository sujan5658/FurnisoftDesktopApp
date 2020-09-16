
package com.furnisoft.dao;

import com.furnisoft.pojos.Category;
import com.furnisoft.pojos.Message;
import java.util.List;

public interface CategoryDAO {
    public Message registerCategory(Category category);
    public List<Category> getCategory();
    public Message updateCategory(Category category);
    public Message deleteCategory(Category category);
}
