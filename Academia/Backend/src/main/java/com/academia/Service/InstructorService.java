package com.academia.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.academia.Model.InstructorModel;
import com.academia.Repository.InstructorRepository;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    //CRUD BASIC OPERATIONS
    public InstructorModel createInstructor(InstructorModel instructor) {
        if (instructorRepository.existsByEmail(instructor.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return instructorRepository.save(instructor);
    }

    public InstructorModel updateInstructor(@NonNull String id, InstructorModel updateInstructor) {
        InstructorModel instructor = instructorRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));
        
        instructor.setUsersTypes(updateInstructor.getUsersTypes());
        instructor.setSpecialty(updateInstructor.getSpecialty());
        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(@NonNull String id) {
        if (!instructorRepository.existsById(id)) {
            throw new IllegalArgumentException("Instructor not found");
        }
        instructorRepository.deleteById(id);
    }

    public InstructorModel getInstructorById(@NonNull String id) {
        return instructorRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));
    }
}
