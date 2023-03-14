package xx.ssm.service;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import xx.ssm.pojo.Student;

import java.util.List;
import java.util.Map;

/**
 * @author 星星
 * @create 2022-11-22 23:56
 */
public interface StudentService {
    /**
     * 查询所有的学生信息
     *
     */
    List<Student> getAllStudent();

    /**
     * 查询学生的分页信息
     * @param pageNum
     * @return
     */
    PageInfo<Student> getStudentPage(Integer pageNum,String keyword);

    /**
     * 根据学号获取学生信息
     * @param stuId
     * @return
     */
    Student getStudentById(Integer stuId);

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    void updateStudent(Student student);

    /**
     * 删除学生信息
     * @param stuId
     */
    void deleteStudent(Integer stuId);

    /**
     * 添加学生信息
     * @param student
     */
    void addStudent(Student student);

    /**
     * 根据关键词查询学生信息
     * @param keyword
     * @return
     */
    List<Student> getStudentByKeyword(String keyword);

    /**
     * 判断登录用户是否合法
     * @param nick
     * @param password
     * @return
     */
    Student login(String nick, String password);


}
