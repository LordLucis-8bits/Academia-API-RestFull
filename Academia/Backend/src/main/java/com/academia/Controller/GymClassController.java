package com.academia.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.academia.model.GymClassModel;
import com.academia.model.UserModel;
import com.academia.service.GymClassService;

@RestController
@RequestMapping("/classes")
public class GymClassController {
    
    @Autowired
    private GymClassService gymClassService;
    
    //CRUD BASICO AULA
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GymClassModel> createClass(@NonNull GymClassModel classModel) {
        GymClassModel createdClass = gymClassService.createClass(classModel);
        return ResponseEntity.ok(createdClass);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN', 'STUDENT')")
    public ResponseEntity<GymClassModel> updateClass(@NonNull @PathVariable String id, GymClassModel updateClass) {
        return ResponseEntity.ok(gymClassService.updateClass(id, updateClass));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClass(@NonNull @PathVariable String id) {
        gymClassService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN, INSTRUCTOR, STUDENT')")
    public ResponseEntity<GymClassModel> getClassById(@NonNull @PathVariable String id) {
        GymClassModel classModel = gymClassService.getClassById(id);
        return ResponseEntity.ok(classModel);
    }

    //REGRA DE NEGÓCIO
    //Iniciar uma aula
    @PatchMapping("/{classId}/start")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Void> startClass(@NonNull @PathVariable String classId, @AuthenticationPrincipal UserModel Instructor) {
        gymClassService.startClass(classId, Instructor.getId());
        return ResponseEntity.ok().build();
    }

    //Finalizar uma aula
    @PatchMapping("/{classId}/finish")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Void> finishClass(@NonNull @PathVariable String classId, @AuthenticationPrincipal UserModel Instructor) {
        gymClassService.finishClass(classId, Instructor.getId());
        return ResponseEntity.ok().build(); 
    }
}
