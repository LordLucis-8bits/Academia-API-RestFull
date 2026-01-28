package com.academia.Model;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.Enum.TypeClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Instructors")
public class InstructorModel extends UserModel {
    private TypeClass specialty;
    private List<String> ClassIds = new ArrayList<>();
}
