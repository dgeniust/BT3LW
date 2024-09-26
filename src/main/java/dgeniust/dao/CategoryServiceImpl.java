package dgeniust.dao;

import java.util.List;

import dgeniust.dao.impl.CategoryDaoImpl;
import dgeniust.dao.impl.ICategoryDao;
import dgeniust.models.CategoryModel;
import dgeniust.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService{
	ICategoryDao cateDao = new CategoryDaoImpl();
	
	@Override
	public List<CategoryModel> findAll() {
		return cateDao.findAll();
	}

	@Override
	public CategoryModel fingById(int id) {
		return cateDao.fingById(id);
	}

	@Override
	public void insert(CategoryModel category) {
		cateDao.insert(category);
		
	}

	@Override
	public void update(CategoryModel category) {
		cateDao.update(category);
	}

	@Override
	public void delete(int id) {
		cateDao.delete(id);
		
	}

	@Override
	public List<CategoryModel> findByName(String keyword) {
		return cateDao.findByName(keyword);
	}

	@Override
	public void softDelete(CategoryModel category) {
		cateDao.softDelete(category);
	}

}
