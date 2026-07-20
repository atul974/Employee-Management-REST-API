-- Flyway Migration: V2__Insert_Sample_Data.sql
-- Seeds sample data for testing

-- =====================================================
-- INSERT DEPARTMENTS
-- =====================================================
INSERT INTO departments (dept_name, description) VALUES
('Information Technology', 'IT Department - Software Development and Infrastructure'),
('Human Resources', 'HR Department - Employee Management and Recruitment'),
('Finance', 'Finance Department - Accounting and Financial Planning'),
('Operations', 'Operations Department - Business Operations and Management'),
('Marketing', 'Marketing Department - Brand and Digital Marketing'),
('Sales', 'Sales Department - Customer Relations and Revenue Generation');

-- =====================================================
-- INSERT DESIGNATIONS
-- =====================================================
INSERT INTO designations (designation_name, description, salary_range_min, salary_range_max) VALUES
('Software Engineer', 'Develops and maintains software applications', 40000, 80000),
('Senior Software Engineer', 'Senior developer with team leadership', 80000, 130000),
('Project Manager', 'Manages projects and team coordination', 50000, 100000),
('Business Analyst', 'Analyzes business requirements', 35000, 70000),
('HR Manager', 'Manages HR operations and recruitment', 45000, 85000),
('Finance Manager', 'Manages financial operations', 50000, 95000),
('Data Analyst', 'Analyzes data and creates reports', 40000, 75000),
('DevOps Engineer', 'Infrastructure and deployment management', 60000, 110000);

-- =====================================================
-- INSERT EMPLOYEES
-- =====================================================
INSERT INTO employees (emp_id, first_name, last_name, email, phone, date_of_birth, gender, address, city, state, pincode, country, salary, dept_id, designation_id, joining_date, status) VALUES
('EMP001', 'Rajesh', 'Kumar', 'rajesh.kumar@company.com', '9876543210', '1990-05-15', 'MALE', '123 Main St', 'Bangalore', 'Karnataka', '560001', 'India', 75000, 1, 1, '2020-01-15', 'ACTIVE'),
('EMP002', 'Priya', 'Singh', 'priya.singh@company.com', '9876543211', '1992-08-22', 'FEMALE', '456 Oak Ave', 'Bangalore', 'Karnataka', '560002', 'India', 85000, 1, 2, '2019-06-10', 'ACTIVE'),
('EMP003', 'Amit', 'Patel', 'amit.patel@company.com', '9876543212', '1991-12-10', 'MALE', '789 Pine Rd', 'Pune', 'Maharashtra', '411001', 'India', 60000, 2, 5, '2021-03-20', 'ACTIVE'),
('EMP004', 'Anjali', 'Sharma', 'anjali.sharma@company.com', '9876543213', '1993-03-05', 'FEMALE', '321 Elm St', 'Hyderabad', 'Telangana', '500001', 'India', 65000, 3, 6, '2020-09-15', 'ACTIVE'),
('EMP005', 'Vikram', 'Verma', 'vikram.verma@company.com', '9876543214', '1988-11-28', 'MALE', '654 Maple Dr', 'Mumbai', 'Maharashtra', '400001', 'India', 95000, 1, 3, '2018-02-01', 'ACTIVE'),
('EMP006', 'Neha', 'Gupta', 'neha.gupta@company.com', '9876543215', '1994-07-14', 'FEMALE', '987 Birch Ln', 'Delhi', 'Delhi', '110001', 'India', 55000, 4, 7, '2022-05-10', 'ACTIVE'),
('EMP007', 'Arjun', 'Mishra', 'arjun.mishra@company.com', '9876543216', '1989-09-30', 'MALE', '147 Cedar Ave', 'Bangalore', 'Karnataka', '560003', 'India', 80000, 1, 2, '2021-01-12', 'ACTIVE'),
('EMP008', 'Deepika', 'Roy', 'deepika.roy@company.com', '9876543217', '1995-01-19', 'FEMALE', '258 Willow St', 'Bangalore', 'Karnataka', '560004', 'India', 45000, 5, 4, '2023-02-15', 'ACTIVE');

-- =====================================================
-- INSERT ATTENDANCE RECORDS
-- =====================================================
INSERT INTO attendance (emp_id, attendance_date, check_in, check_out, status, remarks) VALUES
(1, CURDATE() - INTERVAL 5 DAY, '09:00:00', '18:00:00', 'PRESENT', NULL),
(1, CURDATE() - INTERVAL 4 DAY, '09:15:00', '17:45:00', 'PRESENT', NULL),
(1, CURDATE() - INTERVAL 3 DAY, '10:00:00', '18:30:00', 'PRESENT', 'Late arrival'),
(1, CURDATE() - INTERVAL 2 DAY, '09:00:00', '13:00:00', 'HALF_DAY', 'Doctor appointment'),
(1, CURDATE() - INTERVAL 1 DAY, '09:00:00', '18:00:00', 'PRESENT', NULL),
(2, CURDATE() - INTERVAL 5 DAY, '09:00:00', '18:00:00', 'PRESENT', NULL),
(2, CURDATE() - INTERVAL 4 DAY, '09:00:00', '18:00:00', 'PRESENT', NULL),
(3, CURDATE() - INTERVAL 3 DAY, '09:00:00', '18:00:00', 'PRESENT', NULL),
(3, CURDATE() - INTERVAL 2 DAY, NULL, NULL, 'ABSENT', 'Sick'),
(4, CURDATE() - INTERVAL 1 DAY, '09:00:00', '18:00:00', 'PRESENT', NULL);

-- =====================================================
-- INSERT PAYROLL RECORDS
-- =====================================================
INSERT INTO payroll (emp_id, pay_month, pay_year, basic_salary, dearness_allowance, house_rent_allowance, conveyance_allowance, medical_allowance, other_allowances, gross_salary, provident_fund, professional_tax, income_tax, other_deductions, total_deductions, net_salary, payment_status, payment_date) VALUES
(1, 7, 2026, 75000, 5000, 15000, 2000, 1000, 500, 98500, 7500, 0, 5000, 0, 12500, 86000, 'PAID', '2026-07-05'),
(2, 7, 2026, 85000, 6000, 17000, 2500, 1500, 1000, 113000, 8500, 0, 6000, 0, 14500, 98500, 'PAID', '2026-07-05'),
(3, 7, 2026, 60000, 4000, 12000, 1500, 800, 400, 78700, 6000, 0, 3500, 0, 9500, 69200, 'PAID', '2026-07-05'),
(4, 7, 2026, 65000, 4500, 13000, 1800, 900, 500, 85700, 6500, 0, 4000, 0, 10500, 75200, 'PAID', '2026-07-05'),
(5, 7, 2026, 95000, 7000, 19000, 3000, 2000, 1500, 127500, 9500, 0, 7000, 0, 16500, 111000, 'PAID', '2026-07-05');

-- =====================================================
-- INSERT LEAVE RECORDS
-- =====================================================
INSERT INTO leaves (emp_id, leave_type, start_date, end_date, reason, status, approved_by) VALUES
(1, 'CASUAL', CURDATE() + INTERVAL 10 DAY, CURDATE() + INTERVAL 12 DAY, 'Personal work', 'APPROVED', 2),
(3, 'SICK', CURDATE() + INTERVAL 5 DAY, CURDATE() + INTERVAL 6 DAY, 'Fever', 'PENDING', NULL),
(4, 'EARNED', CURDATE() + INTERVAL 20 DAY, CURDATE() + INTERVAL 25 DAY, 'Vacation', 'APPROVED', 2),
(6, 'CASUAL', CURDATE() - INTERVAL 2 DAY, CURDATE() - INTERVAL 1 DAY, 'Personal emergency', 'APPROVED', 2),
(7, 'SICK', CURDATE() - INTERVAL 5 DAY, CURDATE() - INTERVAL 4 DAY, 'Doctor appointment', 'APPROVED', 2);

-- =====================================================
-- INSERT PERFORMANCE REVIEWS
-- =====================================================
INSERT INTO performance_reviews (emp_id, review_period_start, review_period_end, reviewed_by, rating, comments, strengths, areas_for_improvement, review_date) VALUES
(1, '2026-01-01', '2026-06-30', 2, 5, 'Excellent performer, great team player', 'Problem-solving skills, Leadership qualities', 'Communication could be improved', '2026-07-15'),
(3, '2026-01-01', '2026-06-30', 2, 4, 'Good performance overall', 'Technical skills, Dedication', 'Time management needs improvement', '2026-07-15'),
(4, '2026-01-01', '2026-06-30', 2, 4, 'Solid contributor', 'Attention to detail, Reliability', 'Initiative could be better', '2026-07-15'),
(6, '2026-01-01', '2026-06-30', 5, 3, 'Average performance', 'Punctuality, Basic competency', 'Skills development needed, Performance improvement required', '2026-07-15'),
(7, '2026-01-01', '2026-06-30', 2, 5, 'Outstanding performer', 'Innovation, Quality of work', 'None', '2026-07-15');
