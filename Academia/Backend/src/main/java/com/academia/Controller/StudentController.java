package com.academia.Controller;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.academia.Enum.PlanType;
import com.academia.Model.ClassModel;
import com.academia.Model.StudentModel;
import com.academia.Service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //CRUD BASICO ALUNO
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentModel> createStudent(@RequestBody StudentModel student) {
        StudentModel createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentModel> updateStudent(@NonNull @PathVariable String id, StudentModel updateStudent) {
        return ResponseEntity.ok(studentService.updateStudent(id, updateStudent));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@NonNull @PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', STUDENT')")
    public ResponseEntity<StudentModel> getStudentById(@NonNull @PathVariable String id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    //REGRA DE NEGÓCIO
    //Listar aulas disponiveis para um aluno(ele só vera aulas disponiveis pra se matricular)
    @GetMapping("/classes/available")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<ClassModel>> getAvailableClasses() {
        return ResponseEntity.ok(studentService.listAvailableClasses());
    }

    //Verifica status plano
    @GetMapping("/{id}/plan/status")
    @PreAuthorize("hasAnyRole('ADMIN', STUDENT')")
    public ResponseEntity<Boolean> checkPlanStatus(@NonNull @PathVariable String id) {
        return ResponseEntity.ok(studentService.checkPlanStatus(id));
    }

    //Renovar plano (ADMIN)
    @GetMapping("/{id}/plan/renew")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> renewPlan(@NonNull @PathVariable String id, @RequestParam PlanType newPlanType, @RequestParam LocalDate newLocalDate) {
        studentService.renewPlan(id, newPlanType, newLocalDate);
        return ResponseEntity.noContent().build();
    }
}
