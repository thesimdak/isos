package cz.svetsplhu.isos.service;

import cz.svetsplhu.isos.repository.RopeClimberRepository;
import cz.svetsplhu.isos.service.mapper.RopeClimberMapper;
import cz.svetsplhu.isos.service.model.RopeClimber;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for manipulation with rope climbers.
 */
@Service
public class RopeClimberService {

    private final RopeClimberRepository ropeClimberRepository;
    private final RopeClimberMapper ropeClimberMapper;

    @Inject
    public RopeClimberService(RopeClimberRepository ropeClimberRepository,
                              RopeClimberMapper ropeClimberMapper) {
        this.ropeClimberRepository = ropeClimberRepository;
        this.ropeClimberMapper = ropeClimberMapper;
    }

    @Transactional
    public List<RopeClimber> getAll() {
        return ropeClimberRepository.findAll()
                .stream()
                .map(ropeClimberMapper::map)
                .collect(Collectors.toList());

    }

}
