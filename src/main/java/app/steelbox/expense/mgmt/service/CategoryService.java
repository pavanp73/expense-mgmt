package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.Category;
import app.steelbox.expense.mgmt.model.db.TypeLookup;
import app.steelbox.expense.mgmt.model.enums.Type;
import app.steelbox.expense.mgmt.model.view.CategoryDto;
import app.steelbox.expense.mgmt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TypeLookupService typeLookupService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                           TypeLookupService typeLookupService) {
        this.categoryRepository = categoryRepository;
        this.typeLookupService = typeLookupService;
    }

    public Category addCategory(CategoryDto categoryDto) {

        // for now, it's always EXPENSE type
        TypeLookup typeLookup = typeLookupService.findByType(Type.EXPENSE.getType());
        final Category category = new Category();
        category.setName(categoryDto.getName());
        category.setTypeLookup(typeLookup);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    private CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setType(category.getTypeLookup().getType());
        return categoryDto;
    }


}
