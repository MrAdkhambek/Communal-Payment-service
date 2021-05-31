package r2.llc.communal.model.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import r2.llc.communal.model.data.DistrictModel;
import r2.llc.communal.model.data.EntityMapper;
import r2.llc.communal.model.data.RegionModel;
import r2.llc.communal.model.entity.DistrictEntity;
import r2.llc.communal.model.entity.RegionEntity;
import r2.llc.communal.model.repository.DistrictRepository;
import r2.llc.communal.model.repository.RegionRepository;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RegionService {

    private final EntityMapper<DistrictModel, DistrictEntity> districtMapper;
    private final EntityMapper<RegionModel, RegionEntity> regionMapper;
    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;

    public List<RegionModel> getAll() {
        return regionRepository
                .findAll()
                .stream()
                .map(regionMapper::mapRT)
                .collect(Collectors.toList());
    }

    public RegionModel save(RegionModel regionModel) {
        try {
            RegionEntity entity = regionMapper.mapTR(regionModel);
            RegionEntity result = regionRepository.save(entity);
            return regionMapper.mapRT(result);
        } catch (Exception e) {
            log.error("Error save Region", e);
            throw e;
        }
    }

    public RegionModel getById(Long id) {
        RegionEntity entity = regionRepository.findById(id).orElseThrow();
        return regionMapper.mapRT(entity);
    }

    public RegionModel deleteById(Long id) {
        try {
            return regionRepository
                    .findById(id)
                    .map(entity -> {
                        RegionModel regionModel = regionMapper.mapRT(entity);
                        regionRepository.delete(entity);
                        return regionModel;
                    }).orElseThrow();
        } catch (Exception e) {
            log.error("Error deleteById Region", e);
            throw e;
        }
    }

    public RegionModel addDistrict(Long id, DistrictModel district) {
        try {
            return regionRepository.findById(id)
                    .map(regionEntity -> {
                        DistrictEntity entity = districtMapper.mapTR(district);
                        entity.setRegionId(regionEntity.getId());
                        DistrictEntity districtEntity = districtRepository.save(entity);
                        regionEntity.getDistricts().add(districtEntity);
                        return regionRepository.save(regionEntity);
                    }).map(regionMapper::mapRT)
                    .orElseThrow();
        } catch (Exception e) {
            log.error("Error deleteById Region", e);
            throw e;
        }
    }
}
