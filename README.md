## Fix bug "Remove obj trên bảng nhiều trong quan hệ 1 nhiều mà không chạy xóa trong database"
- **Mô tả:** trong project này, *Major (1)------(n)Student* ta gọi lệnh *em.remove(student)* nhưng kiểm tra database sinh viên vẫn còn.
- **Nguyên nhân:** trên code hướng dẫn cũ, Class Major thiếu phương thức removeStudent
- **Các bước sửa lỗi:**
- Bổ sung phương thức removeStudent trong class Major như sau:
```
  //Xóa sinh viên ra khỏi ngành
  public void removeStudent(Student student){
      students.remove(student);
      student.setMajor(null);
  }
```
- Khi gọi EntityManager xóa sinh viên ta thực hiện các bước sau (xem code đầy đủ trong hàm MainBiDirectional từ dòng code 52 trở đi:
```
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
```
