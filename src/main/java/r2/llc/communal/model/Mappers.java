package r2.llc.communal.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import r2.llc.communal.model.data.*;
import r2.llc.communal.model.entity.*;

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

    @Bean
    public EntityMapper<RegionModel, RegionEntity> regionMapper() {
        return new EntityMapper<>() {
            @Override
            public RegionModel mapRT(RegionEntity from) {
                return RegionModel
                        .builder()
                        .id(from.getId())
                        .titleUz(from.getTitleUz())
                        .titleRu(from.getTitleRu())
                        .titleEn(from.getTitleEn())
                        .districts(from.getDistricts())
                        .createdAt(from.getCreatedAt())
                        .updatedAt(from.getUpdatedAt())
                        .build();
            }

            @Override
            public RegionEntity mapTR(RegionModel from) {
                RegionEntity entity = new RegionEntity();
                entity.setId(from.getId());
                entity.setTitleUz(from.getTitleUz());
                entity.setTitleRu(from.getTitleRu());
                entity.setTitleEn(from.getTitleEn());
                entity.setDistricts(from.getDistricts());
                entity.setCreatedAt(from.getCreatedAt());
                entity.setUpdatedAt(from.getUpdatedAt());
                return entity;
            }
        };
    }

    @Bean
    public EntityMapper<DistrictModel, DistrictEntity> districtMapper() {
        return new EntityMapper<>() {
            @Override
            public DistrictModel mapRT(DistrictEntity from) {
                return DistrictModel
                        .builder()
                        .id(from.getId())
                        .titleUz(from.getTitleUz())
                        .titleRu(from.getTitleRu())
                        .titleEn(from.getTitleEn())
                        .createdAt(from.getCreatedAt())
                        .updatedAt(from.getUpdatedAt())
                        .build();
            }

            @Override
            public DistrictEntity mapTR(DistrictModel from) {
                DistrictEntity entity = new DistrictEntity();
                entity.setId(from.getId());
                entity.setTitleUz(from.getTitleUz());
                entity.setTitleRu(from.getTitleRu());
                entity.setTitleEn(from.getTitleEn());
                entity.setCreatedAt(from.getCreatedAt());
                entity.setUpdatedAt(from.getUpdatedAt());
                return entity;
            }
        };
    }

    @Bean
    public EntityMapper<ReceiptModel, ReceiptEntity> receiptMapper() {
        return new EntityMapper<ReceiptModel, ReceiptEntity>() {
            @Override
            public ReceiptModel mapRT(ReceiptEntity from) {
                return ReceiptModel
                        .builder()
                        .id(from.getId())
                        .summa(from.getSumma())
                        .districtId(from.getDistrictId())
                        .cardExpire(from.getCardExpire())
                        .cardNumber(from.getCardNumber())
                        .communalId(from.getCommunalId())
                        .phoneNumber(from.getPhoneNumber())
                        .createdAt(from.getCreatedAt())
                        .build();
            }

            @Override
            public ReceiptEntity mapTR(ReceiptModel from) {
                final var entity = new ReceiptEntity();
                entity.setId(from.getId());
                entity.setSumma(from.getSumma());
                entity.setDistrictId(from.getDistrictId());
                entity.setCardExpire(from.getCardExpire());
                entity.setCardNumber(from.getCardNumber());
                entity.setCommunalId(from.getCommunalId());
                entity.setPhoneNumber(from.getPhoneNumber());
                return entity;
            }
        };
    }

    @Bean
    public SingleMapper<RegModel, UserEntity> regMapper(PasswordEncoder passwordEncoder) {
        return value -> {
            UserEntity entity = new UserEntity();
            entity.setRole("USER");
            entity.setName(value.getName());
            entity.setPhoto(value.getPhoto());
            entity.setPassword(passwordEncoder.encode(value.getPassword()));
            return entity;
        };
    }
}
