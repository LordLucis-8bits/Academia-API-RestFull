package com.academia.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "enrollments")
public class EnrollmentModel {

    @Id
    private String id;

    private LocalDateTime registrationDate;

    private String studentId;
    
    private String classId;   

}
