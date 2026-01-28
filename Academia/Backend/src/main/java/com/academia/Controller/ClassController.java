package com.academia.Controller;
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
import com.academia.Model.ClassModel;
import com.academia.Model.UserModel;
import com.academia.Service.ClassService;

@RestController
@RequestMapping("/classes")
public class ClassController {
    
    @Autowired
    private ClassService classService;
    
    //CRUD BASICO AULA
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClassModel> createClass(@NonNull ClassModel classModel) {
        ClassModel createdClass = classService.createClass(classModel);
        return ResponseEntity.ok(createdClass);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN', 'STUDENT')")
    public ResponseEntity<ClassModel> updateClass(@NonNull @PathVariable String id, ClassModel updateClass) {
        return ResponseEntity.ok(classService.updateClass(id, updateClass));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClass(@NonNull @PathVariable String id) {
        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN, INSTRUCTOR, STUDENT')")
    public ResponseEntity<ClassModel> getClassById(@NonNull @PathVariable String id) {
        ClassModel classModel = classService.getClassById(id);
        return ResponseEntity.ok(classModel);
    }

    //REGRA DE NEGÓCIO
    //Iniciar uma aula
    @PatchMapping("/{classId}/start")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Void> startClass(@NonNull @PathVariable String classId, @AuthenticationPrincipal UserModel Instructor) {
        classService.startClass(classId, Instructor.getId());
        return ResponseEntity.ok().build();
    }

    //Finalizar uma aula
    @PatchMapping("/{classId}/finish")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<Void> finishClass(@NonNull @PathVariable String classId, @AuthenticationPrincipal UserModel Instructor) {
        classService.finishClass(classId, Instructor.getId());
        return ResponseEntity.ok().build(); 
    }
}
