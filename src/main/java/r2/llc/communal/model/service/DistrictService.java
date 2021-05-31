package r2.llc.communal.model.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import r2.llc.communal.model.data.DistrictModel;
import r2.llc.communal.model.data.EntityMapper;
import r2.llc.communal.model.entity.DistrictEntity;
import r2.llc.communal.model.repository.DistrictRepository;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DistrictService {

    private final EntityMapper<DistrictModel, DistrictEntity> districtMapper;
    private final DistrictRepository repository;

    public List<DistrictModel> getAll() {
        return repository
                .findAll()
                .stream()
                .map(districtMapper::mapRT)
                .collect(Collectors.toList());
    }

    public DistrictModel save(DistrictModel districtModel) {
        try {
            DistrictEntity entity = districtMapper.mapTR(districtModel);
            DistrictEntity result = repository.save(entity);
            return districtMapper.mapRT(result);
        } catch (Exception e) {
            log.error("Error save District", e);
            throw e;
        }
    }

    public DistrictModel getById(Long id) {
        DistrictEntity entity = repository.findById(id).orElseThrow();
        return districtMapper.mapRT(entity);
    }

    public DistrictModel deleteById(Long id) {
        try {
            return repository
                    .findById(id)
                    .map(entity -> {
                        DistrictModel districtModel = districtMapper.mapRT(entity);
                        repository.delete(entity);
                        return districtModel;
                    }).orElseThrow();
        } catch (Exception e) {
            log.error("Error deleteById District", e);
            throw e;
        }
    }
}
