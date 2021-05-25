package r2.llc.communal.model.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import r2.llc.communal.model.data.Communal;
import r2.llc.communal.model.data.EntityMapper;
import r2.llc.communal.model.entity.CommunalEntity;
import r2.llc.communal.model.repository.CommunalRepository;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CommunalService {

    private final EntityMapper<Communal, CommunalEntity> communalMapper;
    private final CommunalRepository repository;

    public List<Communal> getAll() {
        return repository
                .findAll()
                .stream()
                .map(communalMapper::mapRT)
                .collect(Collectors.toList());
    }

    public Communal getById(Long id) {
        CommunalEntity entity = repository.findById(id).orElseThrow();
        return communalMapper.mapRT(entity);
    }

    public Communal save(Communal communal) {
        try {
            CommunalEntity entity = communalMapper.mapTR(communal);
            CommunalEntity result = repository.save(entity);
            return communalMapper.mapRT(result);
        } catch (Exception e) {
            log.error("Error save Communal", e);
            throw e;
        }
    }

    public Communal deleteById(Long id) {
        try {
            CommunalEntity result = repository.getById(id);
            repository.deleteById(id);
            return communalMapper.mapRT(result);
        } catch (Exception e) {
            log.error("Error deleteById Communal", e);
            throw e;
        }
    }
}
