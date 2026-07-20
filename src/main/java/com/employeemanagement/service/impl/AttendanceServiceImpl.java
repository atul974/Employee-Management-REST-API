package com.employeemanagement.service.impl;

import com.employeemanagement.entity.Attendance;
import com.employeemanagement.repository.AttendanceRepository;
import com.employeemanagement.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public Attendance markAttendance(Attendance attendance) {
        log.info("Marking attendance for Employee ID: {} on Date: {}", 
                attendance.getEmployee().getId(), attendance.getAttendanceDate());
        
        Attendance savedAttendance = attendanceRepository.save(attendance);
        log.info("Attendance marked successfully - Employee ID: {}, Status: {}", 
                savedAttendance.getEmployee().getId(), savedAttendance.getStatus());
        
        return savedAttendance;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Attendance> getAttendanceById(Integer id) {
        log.debug("Fetching attendance record with ID: {}", id);
        return attendanceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Attendance> getEmployeeAttendance(Integer empId, LocalDate startDate, LocalDate endDate) {
        log.info("Fetching attendance records for Employee ID: {} between {} and {}", 
                empId, startDate, endDate);
        return attendanceRepository.findByEmployeeIdAndAttendanceDateBetween(empId, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Attendance> getAllAttendance(Pageable pageable) {
        log.debug("Fetching all attendance records with pagination");
        return attendanceRepository.findAll(pageable);
    }

    @Override
    public Attendance updateAttendance(Integer id, Attendance attendanceDetails) {
        log.info("Updating attendance record with ID: {}", id);
        
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Attendance record not found with ID: {}", id);
                    return new IllegalArgumentException("Attendance record not found");
                });
        
        if (attendanceDetails.getStatus() != null) {
            attendance.setStatus(attendanceDetails.getStatus());
        }
        if (attendanceDetails.getCheckIn() != null) {
            attendance.setCheckIn(attendanceDetails.getCheckIn());
        }
        if (attendanceDetails.getCheckOut() != null) {
            attendance.setCheckOut(attendanceDetails.getCheckOut());
        }
        if (attendanceDetails.getRemarks() != null) {
            attendance.setRemarks(attendanceDetails.getRemarks());
        }
        
        Attendance updatedAttendance = attendanceRepository.save(attendance);
        log.info("Attendance record updated successfully with ID: {}", id);
        
        return updatedAttendance;
    }

    @Override
    public void deleteAttendance(Integer id) {
        log.info("Deleting attendance record with ID: {}", id);
        
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Attendance record not found for deletion with ID: {}", id);
                    return new IllegalArgumentException("Attendance record not found");
                });
        
        attendanceRepository.delete(attendance);
        log.info("Attendance record deleted successfully with ID: {}", id);
    }
}
