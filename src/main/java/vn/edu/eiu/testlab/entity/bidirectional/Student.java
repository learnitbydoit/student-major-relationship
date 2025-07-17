package vn.edu.eiu.testlab.entity.bidirectional;

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
    @Column(name = "StudentId")
    private long id;

    @Column(name = "FullName",columnDefinition = "NVARCHAR(100)",nullable = false)
    private String fullName;

    @Column(name = "Gender")
    private Gender gender;

    @Column(name = "Dob")
    private LocalDate dob;

    @Column(name = "Gpa")
    private double gpa;
    //private String majorId; // Kết nối với major ==> database ==> sai, vì hibernate yêu cầu theo obj
    /**
     * Phải có một thuộc tính là obj major để ánh xạ*/
    @ManyToOne //vì bảng sinh viên là
    @JoinColumn(name = "MajorId") //Đặt tên cột khóa ngoại khi ánh xạ xuống bảng
    private Major major; // Sẽ là tên được khai báo trong mappedBy của class Major


    //
    //Tự thêm một constructor đầy đủ tham số mà bỏ Id, vì Id là key tự tăng, không nhập. Major sẽ được thêm vào sau, nên cũng không có

    public Student(String fullName, Gender gender, LocalDate dob, double gpa) {
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.gpa = gpa;
    }
}
