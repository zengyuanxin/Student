package xx.ssm.mapper;

import org.apache.ibatis.annotations.Param;
import xx.ssm.pojo.Student;

import java.util.List;
import java.util.Map;

/**
 * @author 星星
 * @create 2022-11-23 9:51
 */
public interface StudentMapper {

    /**
     * 查询所有学生信息
     * @return
     */
    List<Student> getAllStudent();

    /**
     * 根据id查询学生信息
     * @param stuId
     * @return
     */
    Student getStudentById(@Param("stuId") Integer stuId);

    /**
     * 修改学生信息
     * @param student
     */
    void updateStudent(@Param("student") Student student);

    /**
     * 删除学生信息
     * @param stuId
     */
    void deleteStudent(@Param("stuId") Integer stuId);

    /**
     * 新增学生信息
     * @param student
     */
    void addStudent(@Param("student") Student student);

    /**
     * 根据关键词查询学生信息
     * @param keyword
     * @return
     */
    List<Student> getStudentByKeyword(@Param("keyword") String keyword);
}
