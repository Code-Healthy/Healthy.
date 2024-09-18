package com.healthy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.healthy.model.entity.TrackingRecord;
import java.util.List;

public interface AdminTrackingRecordService {
    List<TrackingRecord> getAll();
    Page<TrackingRecord> paginate(Pageable pageable);
    TrackingRecord findById(int id);
    TrackingRecord create(TrackingRecord trackingrecord);
    TrackingRecord update(Integer id, TrackingRecord updateTrackingRecord);
    void delete(Integer id);
}