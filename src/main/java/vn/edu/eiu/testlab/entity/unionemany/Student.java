package vn.edu.eiu.testlab.entity.unionemany;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.eiu.testlab.entity.Gender;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FullName",columnDefinition = "NVARCHAR(50)",nullable = false)
    private String fullName;

    @Column(name = "Gender")
    private Gender gender;

    @Column(name = "Dob")
    private LocalDate dob; //Tương ứng với kiểu Date trong db

    @Column(name = "EnrolmentYear")
    private int enrolmentYear;

    //Thêm một constructor đầy đủ tham số ngoại trừ khóa chính, vì khóa chính được thiết kế tự động sinh

    public Student(String fullName, Gender gender, LocalDate dob, int enrolmentYear) {
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.enrolmentYear = enrolmentYear;
    }
}
