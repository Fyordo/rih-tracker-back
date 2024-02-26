package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.rentinhand.rihtracker.dto.requests.scrumColumn.ScrumColumnDataRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.repos.ScrumColumnRepository;
import com.rentinhand.rihtracker.services.ScrumColumnService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ScrumColumnServiceImpl implements ScrumColumnService {
    private final ScrumColumnRepository scrumColumnRepository;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public List<ScrumColumn> findAll() {
        return scrumColumnRepository.findAll();
    }

    @Override
    public Collection<ScrumColumn> findAllInProject(Project project) {
        return project.getColumns();
    }

    @Override
    public Optional<ScrumColumn> findById(Long scrumColumnId) {
        return scrumColumnRepository.findById(scrumColumnId);
    }

    @Override
    public ScrumColumn createScrumColumn(ScrumColumnDataRequest scrumColumnData, Project project) {
        ScrumColumn scrumColumn = mapper.map(scrumColumnData, ScrumColumn.class);
        scrumColumn.setProject(project);
        scrumColumnRepository.save(scrumColumn);
        return scrumColumn;
    }

    @Override
    public ScrumColumn updateScrumColumn(ScrumColumn scrumColumn, ScrumColumnDataRequest scrumColumnData) {
        scrumColumnRepository.updateScrumColumn(
                scrumColumnData.getTitle(), 
                scrumColumnData.getColor(),
                scrumColumn.getId()
        );
        return scrumColumn;
    }

    @Override
    public void deleteScrumColumn(ScrumColumn scrumColumn) {
        scrumColumnRepository.delete(scrumColumn);
    }
}