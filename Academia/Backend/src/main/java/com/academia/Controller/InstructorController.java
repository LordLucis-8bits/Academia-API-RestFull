package com.academia.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.academia.Model.InstructorModel;
import com.academia.Service.InstructorService;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    //CRUD BASICO INSTRUTOR
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InstructorModel> createInstructor(@NonNull InstructorModel instructor) {
        return ResponseEntity.ok(instructorService.createInstructor(instructor));
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InstructorModel> updateInstructor(@NonNull @PathVariable String id, InstructorModel updateInstructor) {
        return ResponseEntity.ok(instructorService.updateInstructor(id, updateInstructor));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteInstructor(@NonNull @PathVariable String id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<InstructorModel> getInstructorById(@NonNull @PathVariable String id) {
        InstructorModel instructor = instructorService.getInstructorById(id);
        return ResponseEntity.ok(instructor);
    }
}
