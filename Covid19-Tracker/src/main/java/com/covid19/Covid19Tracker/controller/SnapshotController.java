package com.covid19.Covid19Tracker.controller;

import com.covid19.Covid19Tracker.model.Snapshot;
import com.covid19.Covid19Tracker.model.User;
import com.covid19.Covid19Tracker.repository.SnapshotRepository;
import com.covid19.Covid19Tracker.service.SecurityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SnapshotController {

    private final SnapshotRepository snapshotRepository;
    private final SecurityService securityService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public SnapshotController(SnapshotRepository snapshotRepository, SecurityService securityService) {
        this.snapshotRepository = snapshotRepository;
        this.securityService = securityService;
    }

    @GetMapping("/snapshots")
    public List<Snapshot> all() {
        return snapshotRepository.findAllByUser(securityService.getLoggedIn());
    }

    @PostMapping("/snapshot")
    public Snapshot newSnapshot(@RequestBody Snapshot snapshot) {
        snapshot.setId(UUID.randomUUID().toString());
        snapshot.setUser(securityService.getLoggedIn());
        return snapshotRepository.save(snapshot);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/snapshots/{id}")
    public Snapshot updateSnapshot(@RequestBody Snapshot newSnapshot, @PathVariable String id) {
        return snapshotRepository.findById(id)
                .map((snapshot)-> {
                    User user = securityService.getLoggedIn();
                    if (!snapshot.getUser().equals(user)) {
                        return null;
                    }

                    snapshot.update(newSnapshot);
                    return snapshotRepository.save(snapshot);
                })
                .orElse(null);
    }

    @DeleteMapping("/snapshots/{id}")
    public Snapshot deleteSnapshot(@PathVariable String id) {
        return snapshotRepository.findById(id)
                .map((snapshot)-> {
                    User user = securityService.getLoggedIn();
                    if (!snapshot.getUser().equals(user)) {
                        return null;
                    }

                    return snapshot;
                })
                .orElse(null);
    }
}
