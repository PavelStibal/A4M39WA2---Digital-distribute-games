package digital.distribute.games.controller;

import digital.distribute.games.entity.Category;
import digital.distribute.games.service.CategoryService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@Model
public class CategoryController {

    @Inject
    private Logger logger;

    @Inject
    private CategoryService categoryService;

    @Produces
    @Named(value = "newCategory")
    private Category newCategory;

    @PostConstruct
    public void initNewCategory() {
        newCategory = new Category();
    }

    public List<Category> getAllCategories() throws IOException {
        try {
            List<Category> categories = categoryService.findAll();
            logger.fine("Categories received.");
            return categories;
        } catch (Exception e) {
            logger.severe("Error during getAllCategories(): " + e.getMessage());
            throw new WebApplicationException();
        }
    }

    public Category getCategory(final long id) throws IOException {
        try {
            Category category = categoryService.get(id);
            logger.fine("Category with id " + id + " found.");
            return category;
        } catch (Exception e) {
            logger.severe("Error during getCategory() " + id + ": " + e.getMessage());
            throw new WebApplicationException();
        }
    }
}
