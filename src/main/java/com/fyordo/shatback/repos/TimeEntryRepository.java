package com.fyordo.shatback.repos;

import com.fyordo.shatback.entities.Project;
import com.fyordo.shatback.entities.Task;
import com.fyordo.shatback.entities.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimeEntryRepository extends JpaRepository<TimeEntry, Long> {
    @Transactional
    @Modifying
    @Query("update TimeEntry t set t.timeStart = ?1, t.timeEnd = ?2, t.description = ?3, t.task = ?4 where t.id = ?5")
    void updateTimeEntry(LocalDateTime timeStart, LocalDateTime timeEnd, String description, Task task, Long id);

    Optional<TimeEntry> findByUser_IdAndTimeStartBeforeAndTimeEndNull(Long id, LocalDateTime timeStart);

    @Transactional
    @Modifying
    @Query("update TimeEntry t set t.timeEnd = ?1 where t.id = ?2")
    int setTimeEnd(LocalDateTime timeEnd, Long id);

    List<TimeEntry> findAllByTimeStartBetween(LocalDateTime start, LocalDateTime end);


    @Query("""
            select t from TimeEntry t
            where t.user.id = ?1 and t.task.scrumColumn.project = ?2 and t.timeStart between ?3 and ?4 and t.timeEnd is not null""")
    List<TimeEntry> findAllByUserAndDateAndProject(Long id, Project project, LocalDateTime timeStartStart, LocalDateTime timeStartEnd);

}