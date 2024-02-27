package com.rentinhand.rihtracker.repos;

import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface TimeEntryRepository extends JpaRepository<TimeEntry, Long> {
    @Transactional
    @Modifying
    @Query("update TimeEntry t set t.timeStart = ?1, t.timeEnd = ?2, t.description = ?3, t.task = ?4 where t.id = ?5")
    void updateTimeEntry(LocalDateTime timeStart, LocalDateTime timeEnd, String description, Task task, Long id);
}