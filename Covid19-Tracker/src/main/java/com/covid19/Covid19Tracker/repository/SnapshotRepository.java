package com.covid19.Covid19Tracker.repository;

import com.covid19.Covid19Tracker.model.Snapshot;
import com.covid19.Covid19Tracker.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnapshotRepository extends CrudRepository<Snapshot, String> {

    List<Snapshot> findAllByUser(User user);
}
