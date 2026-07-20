package com.employeemanagement.service.impl;

import com.employeemanagement.entity.Employee;
import com.employeemanagement.entity.Department;
import com.employeemanagement.entity.Designation;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        log.info("Creating new employee with ID: {}, Email: {}", employee.getEmpId(), employee.getEmail());
        
        // Validate employee
        if (employeeRepository.existsByEmpId(employee.getEmpId())) {
            log.warn("Employee creation failed - Employee ID already exists: {}", employee.getEmpId());
            throw new IllegalArgumentException("Employee ID already exists: " + employee.getEmpId());
        }
        
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            log.warn("Employee creation failed - Email already exists: {}", employee.getEmail());
            throw new IllegalArgumentException("Email already exists: " + employee.getEmail());
        }
        
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee created successfully with ID: {} and Database ID: {}", 
                savedEmployee.getEmpId(), savedEmployee.getId());
        
        return savedEmployee;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> getEmployeeById(Integer id) {
        log.debug("Fetching employee by ID: {}", id);
        return employeeRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> getEmployeeByEmpId(String empId) {
        log.debug("Fetching employee by Employee ID: {}", empId);
        return employeeRepository.findByEmpId(empId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> getEmployeeByEmail(String email) {
        log.debug("Fetching employee by Email: {}", email);
        return employeeRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employee> getAllEmployees(Pageable pageable) {
        log.debug("Fetching all employees with pagination: page={}, size={}", 
                pageable.getPageNumber(), pageable.getPageSize());
        return employeeRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByDepartment(Integer deptId) {
        log.debug("Fetching employees by Department ID: {}", deptId);
        return employeeRepository.findByDepartmentId(deptId);
    }

    @Override
    public Employee updateEmployee(Integer id, Employee employeeDetails) {
        log.info("Updating employee with ID: {}", id);
        
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found for update with ID: {}", id);
                    return new IllegalArgumentException("Employee not found with ID: " + id);
                });
        
        if (employeeDetails.getFirstName() != null) {
            employee.setFirstName(employeeDetails.getFirstName());
        }
        if (employeeDetails.getLastName() != null) {
            employee.setLastName(employeeDetails.getLastName());
        }
        if (employeeDetails.getEmail() != null && !employeeDetails.getEmail().equals(employee.getEmail())) {
            if (employeeRepository.existsByEmail(employeeDetails.getEmail())) {
                log.warn("Update failed - Email already exists: {}", employeeDetails.getEmail());
                throw new IllegalArgumentException("Email already exists: " + employeeDetails.getEmail());
            }
            employee.setEmail(employeeDetails.getEmail());
        }
        if (employeeDetails.getPhone() != null) {
            employee.setPhone(employeeDetails.getPhone());
        }
        if (employeeDetails.getSalary() != null) {
            employee.setSalary(employeeDetails.getSalary());
        }
        if (employeeDetails.getStatus() != null) {
            employee.setStatus(employeeDetails.getStatus());
        }
        
        Employee updatedEmployee = employeeRepository.save(employee);
        log.info("Employee updated successfully with ID: {}", id);
        
        return updatedEmployee;
    }

    @Override
    public void deleteEmployee(Integer id) {
        log.info("Deleting employee with ID: {}", id);
        
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found for deletion with ID: {}", id);
                    return new IllegalArgumentException("Employee not found with ID: " + id);
                });
        
        employeeRepository.delete(employee);
        log.info("Employee deleted successfully with ID: {} and Employee ID: {}", id, employee.getEmpId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> searchEmployees(String keyword) {
        log.debug("Searching employees with keyword: {}", keyword);
        return employeeRepository.searchEmployees(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalEmployeeCount() {
        log.debug("Fetching total employee count");
        return employeeRepository.count();
    }
}
