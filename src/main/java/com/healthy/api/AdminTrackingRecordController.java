package com.healthy.api;

import com.healthy.model.entity.TrackingRecord;
import com.healthy.service.AdminTrackingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/trackingrecords")
public class AdminTrackingRecordController {
    private final AdminTrackingRecordService adminTrackingRecordService;

    @GetMapping
    public ResponseEntity<List<TrackingRecord>> getAllTrackingRecords(){
        List<TrackingRecord> trackingrecords = adminTrackingRecordService.getAll();
        return new ResponseEntity<List<TrackingRecord>>(trackingrecords, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<TrackingRecord>> paginateTrackingRecords(
            @PageableDefault(size=10, sort="date") Pageable pageable)
    {
        Page<TrackingRecord> trackingrecords = adminTrackingRecordService.paginate(pageable);
        return new ResponseEntity<Page<TrackingRecord>>(trackingrecords,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TrackingRecord> getTrackingRecordById(@PathVariable("id") Integer id){
        TrackingRecord trackingrecord = adminTrackingRecordService.findById(id);
        return new ResponseEntity<TrackingRecord>(trackingrecord,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TrackingRecord> createTrackingRecord(@RequestBody TrackingRecord trackingrecord){
        TrackingRecord newTrackingRecord = adminTrackingRecordService.create(trackingrecord);
        return new ResponseEntity<TrackingRecord>(newTrackingRecord,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackingRecord> updateTrackingRecord(@PathVariable("id") Integer id,
                                           @RequestBody TrackingRecord trackingrecord){
        TrackingRecord updatedTrackingRecord = adminTrackingRecordService.update(id, trackingrecord);
        return new ResponseEntity<TrackingRecord>(updatedTrackingRecord,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrackingRecord> deletePlan(@PathVariable("id") Integer id){
        adminTrackingRecordService.delete(id);
        return new ResponseEntity<TrackingRecord>(HttpStatus.NO_CONTENT);
    }
}