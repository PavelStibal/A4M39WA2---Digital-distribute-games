package digital.distribute.games.service.impl;

import digital.distribute.games.dao.CategoryDAO;
import digital.distribute.games.entity.Category;
import digital.distribute.games.service.CategoryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@Stateless
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final Logger logger;

    private final CategoryDAO categoryDAO;

    @Inject
    public CategoryServiceImpl(Logger logger, CategoryDAO categoryDAO) {
        this.logger = logger;
        this.categoryDAO = categoryDAO;
    }

    public Category get(Long id) {
        final Category category = categoryDAO.find(id);
        logger.info("Returning category: " + category);
        return category;
    }

    public void create(Category category) {
        categoryDAO.save(category);
        logger.info("Created a new category with id: " + category.getId());
    }

    public void delete(Long id) {
        delete(get(id));
    }

    public void delete(Category category) {
        categoryDAO.delete(category);
        logger.info("Category with id " + category.getId() + " deleted");
    }

    public void update(Category category) {
        categoryDAO.update(category);
        logger.info("Category with id " + category.getId() + " updated");
    }

    public List<Category> findAll() {
        List<Category> categories = categoryDAO.findAll();
        logger.info("Returning " + categories.size() + " categories.");
        return categories;
    }
}
