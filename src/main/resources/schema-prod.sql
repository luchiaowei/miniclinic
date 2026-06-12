-- 1. 建立 doctor 表
CREATE TABLE IF NOT EXISTS doctor (
    doctor_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(100),
    specialty VARCHAR(255),
    password_hash VARCHAR(255)
);

-- 2. 建立 patient 表
CREATE TABLE IF NOT EXISTS patient (
    chart_no VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100),
    gender VARCHAR(10),
    birth_date DATE,
    phone VARCHAR(50)
);

-- 3. 建立 appointment 表
CREATE TABLE IF NOT EXISTS appointment (
    appt_id VARCHAR(50) PRIMARY KEY,
    chart_no VARCHAR(50),
    doctor_id VARCHAR(50),
    appt_date DATE,
    time_slot VARCHAR(10),
    status VARCHAR(20)
);