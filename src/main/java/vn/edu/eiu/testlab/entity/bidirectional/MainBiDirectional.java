package vn.edu.eiu.testlab.entity.bidirectional;

import jakarta.persistence.EntityManager;
import vn.edu.eiu.testlab.entity.Gender;
import vn.edu.eiu.testlab.infra.JpaUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Các bước test:
 * #1. Tạo mới major
 * #2. Tạo mới student
 * #3. addStudent() vào major, và set major cho student
 * #4. Ánh xạ Major xuống  database (student sẽ tự động ánh xạ theo)*/

public class MainBiDirectional {
    public static void main(String[] args) {
        //#1.
        Major cseMajor = new Major("CSE","Software Engineering");
        Major cswMajor = new Major("CSW","Networking and Data Transportation");
        //#2. Tạo mới student
        Student std1 = new Student("Nhất trương", Gender.MALE, LocalDate.parse("2000-02-05"),8.1);
        Student std2 = new Student("Nhị Nguyễn", Gender.FEMALE, LocalDate.parse("2001-05-15"),8.4);
        Student std3 = new Student("Tam Lương", Gender.FEMALE, LocalDate.parse("2000-12-25"),8.1);
        Student std4 = new Student("Tứ Lý", Gender.MALE, LocalDate.parse("2001-03-13"),8.1);
        System.out.println(std4);
        //#3.1 Thêm student vào major
        //Cách 1
        cseMajor.addStudent(std1);
        cseMajor.addStudent(std2);

        //Cách 2
        /* Nếu dùng cách này thì phải kiểm tra major của sinh viên trước khi set, có đúng với major đã add sinh viên không.*/
        List<Student> cswStudents = new ArrayList<>();
        cswStudents.add(std3);
        cswStudents.add(std4);
        cswMajor.setStudents(cswStudents);
//        //3.2 set major cho student
//        std1.setMajor(cswMajor);
//        std2.setMajor(cseMajor);
        std3.setMajor(cswMajor);
        std4.setMajor(cswMajor);

        //#4. Đẩy xuống DB
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(cseMajor);
        em.persist(cswMajor);
        em.getTransaction().commit();

        //Xóa thử student 1
        //Bước 1 tìm student và major (cha) của student
        Student std5 = em.find(Student.class, 1);
        Major std5Major = em.find(Major.class, std5.getMajor().getMajorId());

        //Bước 2: Ngắt mối quan hệ với cha
        std5Major.removeStudent(std5);

        //Bước 3: Xóa
        em.getTransaction().begin();
        em.remove(std5);
        System.out.println("Deleted std5");
        em.getTransaction().commit();
        em.close();

        //
    }

}
