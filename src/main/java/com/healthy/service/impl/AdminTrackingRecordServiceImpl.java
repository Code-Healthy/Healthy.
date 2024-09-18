package com.healthy.service.impl;

import com.healthy.model.entity.TrackingRecord;
import com.healthy.repository.TrackingRecordRepository;
import com.healthy.service.AdminTrackingRecordService;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminTrackingRecordServiceImpl implements AdminTrackingRecordService {

    private final TrackingRecordRepository trackingRecordRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TrackingRecord> getAll() {
        return trackingRecordRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TrackingRecord> paginate(Pageable pageable) {
        return trackingRecordRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public TrackingRecord findById(int id) {
        return trackingRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("Tracking Record not found"));
    }

    @Transactional
    @Override
    public TrackingRecord create(TrackingRecord trackingrecord) {
        trackingrecord.setDate(LocalDateTime.now());
        return trackingRecordRepository.save(trackingrecord);
    }

    @Transactional
    @Override
    public TrackingRecord update(Integer id, TrackingRecord updateTrackingRecord) {
        TrackingRecord trackingRecordFromDb = findById(id);
        trackingRecordFromDb.setDate(updateTrackingRecord.getDate());
        trackingRecordFromDb.setId(updateTrackingRecord.getId());
        trackingRecordFromDb.setGoal(updateTrackingRecord.getGoal());
        trackingRecordFromDb.setUser(updateTrackingRecord.getUser());
        trackingRecordFromDb.setNote(updateTrackingRecord.getNote());
        trackingRecordFromDb.setValue(updateTrackingRecord.getValue());
        return trackingRecordRepository.save(trackingRecordFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
    TrackingRecord trackingrecord = findById(id);
    trackingRecordRepository.delete(trackingrecord);
    }
}