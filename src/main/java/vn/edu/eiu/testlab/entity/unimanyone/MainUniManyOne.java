package vn.edu.eiu.testlab.entity.unimanyone;

import jakarta.persistence.EntityManager;
import vn.edu.eiu.testlab.entity.Gender;
import vn.edu.eiu.testlab.infra.JpaUtil;

import java.time.LocalDate;

/** Để ánh xạ xuống database ta cần làm các bước sau:
 * 1. Tạo mới obj major
 * 2. Tạo mới obj student (2 3 obj)
 * 3. Thêm major cho sinh viên
 * 4. Ánh xạ major trước, sinh viên sau*/

public class MainUniManyOne {
    public static void main(String[] args) {
        //Test ánh xạ uni direction theo chiều nhiều - một
        //#1
        Major cseMajor = new Major("CSE","Software Engineering - Kỹ Thuật Phầm Mềm");
        //#2
        Student std1 = new Student("Nhất Trương", Gender.MALE, LocalDate.parse("2000-10-10"),8.6);
        Student std2 = new Student("Nhị Nguyễn", Gender.FEMALE, LocalDate.parse("2001-12-15"),9.6);
        //#3 Đưa sinh viên vào chuyên ngành
        std1.setMajor(cseMajor);
        std2.setMajor(cseMajor);
        //#4 Ánh xạ xuống DB nhờ EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(cseMajor);
        em.persist(std1);
        em.persist(std2);
        em.getTransaction().commit();
        em.close();

    }
}
