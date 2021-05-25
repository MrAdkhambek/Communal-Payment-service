package r2.llc.communal.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import r2.llc.communal.model.data.Category;
import r2.llc.communal.model.data.Communal;
import r2.llc.communal.model.data.EntityMapper;
import r2.llc.communal.model.data.UserModel;
import r2.llc.communal.model.entity.CategoryEntity;
import r2.llc.communal.model.entity.CommunalEntity;
import r2.llc.communal.model.entity.UserEntity;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Mappers {

    @Bean
    public EntityMapper<Category, CategoryEntity> categoryMapper(@Autowired EntityMapper<Communal, CommunalEntity> communalMapper) {
        return new EntityMapper<>() {
            @Override
            public Category mapRT(CategoryEntity from) {
                Set<Communal> communalSet = Optional
                        .ofNullable(from.getItems())
                        .orElse(Set.of())
                        .stream()
                        .map(communalMapper::mapRT)
                        .collect(Collectors.toSet());

                Category category = new Category();
                category.setId(from.getId());
                category.setName(from.getName());
                category.setPhoto(from.getPhoto());
                category.setItems(communalSet);
                category.setCreatedAt(from.getCreatedAt());
                category.setUpdatedAt(from.getUpdatedAt());
                return category;
            }

            @Override
            public CategoryEntity mapTR(Category from) {
                Set<CommunalEntity> communalSet = Optional
                        .ofNullable(from.getItems())
                        .orElse(Set.of())
                        .stream()
                        .map(communalMapper::mapTR)
                        .collect(Collectors.toSet());

                CategoryEntity entity = new CategoryEntity();
                entity.setId(from.getId());
                entity.setName(from.getName());
                entity.setPhoto(from.getPhoto());
                entity.setItems(communalSet);
                entity.setCreatedAt(from.getCreatedAt());
                entity.setUpdatedAt(from.getUpdatedAt());
                return entity;
            }
        };
    }

    @Bean
    public EntityMapper<Communal, CommunalEntity> communalMapper() {
        return new EntityMapper<>() {
            @Override
            public Communal mapRT(CommunalEntity from) {
                Communal communal = new Communal();
                communal.setId(from.getId());
                communal.setName(from.getName());
                communal.setPhoto(from.getPhoto());
                communal.setMax(from.getMax());
                communal.setMin(from.getMin());
                communal.setCreatedAt(from.getCreatedAt());
                communal.setUpdatedAt(from.getUpdatedAt());
                return communal;
            }

            @Override
            public CommunalEntity mapTR(Communal from) {
                CommunalEntity entity = new CommunalEntity();
                entity.setId(from.getId());
                entity.setName(from.getName());
                entity.setPhoto(from.getPhoto());
                entity.setMax(from.getMax());
                entity.setMin(from.getMin());
                entity.setCreatedAt(from.getCreatedAt());
                entity.setUpdatedAt(from.getUpdatedAt());
                return entity;
            }
        };
    }


    @Bean
    public EntityMapper<UserModel, UserEntity> userMapper() {
        return new EntityMapper<>() {
            @Override
            public UserModel mapRT(UserEntity from) {
                UserModel model = new UserModel();
                model.setId(from.getId());
                model.setRole(from.getRole());
                model.setName(from.getName());
                model.setPhoto(from.getPhoto());
                model.setToken(from.getToken());
                model.setPassword(from.getPassword());
                model.setCreatedAt(from.getCreatedAt());
                model.setUpdatedAt(from.getUpdatedAt());
                return model;
            }

            @Override
            public UserEntity mapTR(UserModel from) {
                UserEntity entity = new UserEntity();
                entity.setId(from.getId());
                entity.setRole(from.getRole());
                entity.setName(from.getName());
                entity.setPhoto(from.getPhoto());
                entity.setToken(from.getToken());
                entity.setPassword(from.getPassword());
                entity.setCreatedAt(from.getCreatedAt());
                entity.setUpdatedAt(from.getUpdatedAt());
                return entity;
            }
        };
    }
}
