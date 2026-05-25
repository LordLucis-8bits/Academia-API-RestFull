package com.academia.service;

import org.springframework.stereotype.Service;

import com.academia.dto.admin.UpdateUserDTO;
import com.academia.dto.instructor.InstructorResponseDTO;
import com.academia.dto.instructor.UpdateInstructorDTO;
import com.academia.model.InstructorModel;
import com.academia.model.UserModel;
import com.academia.repository.InstructorRepository;
import com.academia.repository.UserRepository;
import com.academia.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    private final UserRepository userRepository;

    public InstructorResponseDTO updateInstructor(String userId, String instructorId, UpdateUserDTO userDTO, UpdateInstructorDTO instructorDTO) {
        UserModel user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        InstructorModel instructor = instructorRepository.findById(instructorId)
        .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));

        if (userDTO != null) {
            if (userDTO.getName() != null) {
                user.setName(userDTO.getName());
            }
        
            if (userDTO.getEmail() != null) {
                user.setEmail(userDTO.getEmail());
            }

            if (userDTO.getAge() != null) {
                user.setAge(userDTO.getAge());
            }
        }
    
        userRepository.save(user);

        if (instructor.getSpecialty() != null) {
            if (instructorDTO.getSpecialty() != null) {
                instructor.setSpecialty(instructorDTO.getSpecialty());            
            }
        }
        
        instructorRepository.save(instructor);

        return new InstructorResponseDTO(instructor, user);
    }

    public void deleteInstructor(String id) {
        if (!instructorRepository.existsById(id)) {
            throw new IllegalArgumentException("Instructor not found");
        }

        instructorRepository.deleteById(id);
    }

    public InstructorResponseDTO getInstructorById(String id) {
        InstructorModel instructor = instructorRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));

        UserModel user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return new InstructorResponseDTO(instructor, user);
    }

    public InstructorResponseDTO getCurrentInstructorInfo() {
        UserModel user = SecurityUtils.getCurrentUser();

        InstructorModel instructor = instructorRepository.findById(user.getId())
        .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));

        return new InstructorResponseDTO(instructor, user);
    }
}
