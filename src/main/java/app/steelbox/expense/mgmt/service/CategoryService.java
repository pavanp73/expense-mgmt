package app.steelbox.expense.mgmt.service;

import app.steelbox.expense.mgmt.model.db.Category;
import app.steelbox.expense.mgmt.model.db.TypeLookup;
import app.steelbox.expense.mgmt.model.enums.TransactionType;
import app.steelbox.expense.mgmt.model.view.CategoryDto;
import app.steelbox.expense.mgmt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Category> addCategory(List<CategoryDto> categoryDtoList) {
        List<Category> categories = categoryDtoList.stream().map(this::mapToEntity).toList();
        return categoryRepository.saveAll(categories);
    }

    public Category addCategory(CategoryDto categoryDto) {
        return categoryRepository.save(mapToEntity(categoryDto));
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    private CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setCategoryType(category.getTypeLookup().getType());
        return categoryDto;
    }

    private Category mapToEntity(CategoryDto categoryDto) {
        // for now, it's always EXPENSE type
        TypeLookup typeLookup = typeLookupService.findByType(TransactionType.EXPENSE.getType());
        final Category category = new Category();
        category.setName(categoryDto.getName());
        category.setTypeLookup(typeLookup);
        return category;
    }


}
