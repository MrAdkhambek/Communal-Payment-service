package r2.llc.communal.model.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import r2.llc.communal.model.data.Category;
import r2.llc.communal.model.data.Communal;
import r2.llc.communal.model.data.EntityMapper;
import r2.llc.communal.model.entity.CategoryEntity;
import r2.llc.communal.model.entity.CommunalEntity;
import r2.llc.communal.model.repository.CategoryRepository;
import r2.llc.communal.model.repository.CommunalRepository;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CategoryService {

    private final EntityMapper<Category, CategoryEntity> categoryMapper;
    private final EntityMapper<Communal, CommunalEntity> communalMapper;
    private final CommunalRepository communalRepository;
    private final CategoryRepository repository;

    public List<Category> getAll() {
        return repository
                .findAll()
                .stream()
                .map(categoryMapper::mapRT)
                .collect(Collectors.toList());
    }

    public Category save(Category category) {
        try {
            CategoryEntity entity = categoryMapper.mapTR(category);
            CategoryEntity result = repository.save(entity);
            return categoryMapper.mapRT(result);
        } catch (Exception e) {
            log.error("Error save Category", e);
            throw e;
        }
    }

    public Category getById(Long id) {
        CategoryEntity entity = repository.findById(id).orElseThrow();
        return categoryMapper.mapRT(entity);
    }

    public Category deleteById(Long id) {
        try {
            return repository
                    .findById(id)
                    .map(entity -> {
                        Category category = categoryMapper.mapRT(entity);
                        repository.delete(entity);
                        return category;
                    }).orElseThrow();
        } catch (Exception e) {
            log.error("Error deleteById Category", e);
            throw e;
        }
    }

    public Category addCommunal(Long id, Communal communal) {
        try {
            return repository.findById(id)
                    .map(categoryEntity -> {
                        CommunalEntity entity = communalMapper.mapTR(communal);
                        entity.setCategory(categoryEntity.getId());
                        CommunalEntity tempCommunal = communalRepository.save(entity);
                        categoryEntity.getItems().add(tempCommunal);
                        return repository.save(categoryEntity);
                    }).map(categoryMapper::mapRT)
                    .orElseThrow();
        } catch (Exception e) {
            log.error("Error deleteById Category", e);
            throw e;
        }
    }
}
