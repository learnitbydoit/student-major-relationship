package vn.edu.eiu.testlab.entity.bidirectional;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbl_Major")
public class Major {
    @Id
    @Column(name = "MajorId",columnDefinition = "CHAR(4)")
    private String majorId;

    @Column(name = "MajorName", columnDefinition = "NVARCHAR(100)",nullable = false)
    private String majorName;


    /* Một major có nhiều sinh viên thuộc về major đó. Hay nói theo oop thì trong class major sẽ có một danh sách sinh viên là thuộc tính.
     * Thiết kế quan hệ đơn hướng nhìn từ bảng 1 ta làm như sau:
     * fetchtype: EAGER: khi load major thì lập tức load tất cả student thuộc major đó |LAZY: chỉ load major, student chỉ load khi được gọi bằng hàm get()
     * CascadeType: Khi major được thực hiện thao tác gì thì tự động student thuộc major đó sẽ lập tức bị tác động theo. ALL: tất cả các thao tác.
     * //Đối với binary directional, join column sẽ thiết lập trên class đóng vai trò bảng nhiều. Nên sẽ không khai báo ở đây.
     * Bổ sung thêm thuộc tính mappedBy = "tên đối tượng major là thuộc tính của bảng student"*/
    //@JoinColumn(name = "MajorId")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy ="major")
    private List<Student> students = new ArrayList<>();

    //Khi có sinh viên thuộc major nào thì phải thêm vào List của major đó:
    public void addStudent(Student student){
        //Thêm student vào danh sách student của major
        students.add(student);

        //Đồng thời thêm major cho student
        student.setMajor(this);
    }
    //Tạo một constructor không có tham số List student, vì student sẽ được thêm sau

    public Major(String majorId, String majorName) {
        this.majorId = majorId;
        this.majorName = majorName;
    }
}
