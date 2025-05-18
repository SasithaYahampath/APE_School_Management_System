CREATE DATABASE users_bd;

use users_bd;

CREATE TABLE students (
  student_id varchar(10) primary key NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  grade varchar(20) NOT NULL,
  email varchar(100) NOT NULL,
  status enum('Active','Inactive') DEFAULT 'Active',
  photo_path varchar(255) DEFAULT 'images/default.png'
);


CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  email varchar(100) NOT NULL,
  password varchar(255) NOT NULL,
  created_at timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY username_unique (username),
  UNIQUE KEY email_unique (email)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE courses (
  course_id int NOT NULL AUTO_INCREMENT,
  course_code varchar(20) NOT NULL,
  course_name varchar(100) NOT NULL,
  department varchar(50) NOT NULL,
  credits int NOT NULL,
  PRIMARY KEY (course_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE lecture_slides (
  slide_id int NOT NULL AUTO_INCREMENT,
  course_id int NOT NULL,
  title varchar(100) NOT NULL,
  description text,
  file_name varchar(255) NOT NULL,
  file_path varchar(255) NOT NULL,
  upload_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (slide_id),
  KEY lecture_slides_ibfk_1 (course_id),
  CONSTRAINT lecture_slides_ibfk_1 FOREIGN KEY (course_id) 
    REFERENCES courses (course_id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE attendance (
  attendance_id int NOT NULL AUTO_INCREMENT,
  student_id varchar(10) DEFAULT NULL,
  date date NOT NULL,
  time_in time DEFAULT NULL,
  time_out time DEFAULT NULL,
  status decimal(5,2) DEFAULT '0.00',
  notes varchar(255) DEFAULT NULL,
  PRIMARY KEY (attendance_id),
  KEY student_id (student_id),
  CONSTRAINT attendance_ibfk_1 FOREIGN KEY (student_id) 
    REFERENCES students (student_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;