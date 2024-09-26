package dgeniust.dao.impl;

import java.util.List;

import dgeniust.models.CategoryModel;

public interface ICategoryDao {
	List<CategoryModel> findAll();
	
	CategoryModel fingById(int id);
	
	void insert(CategoryModel category);
	
	void update(CategoryModel category);
	
	void delete(int id);
	
	List<CategoryModel> findByName(String keyword);
	
	void softDelete(CategoryModel category);
}
