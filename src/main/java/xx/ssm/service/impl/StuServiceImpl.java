package xx.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xx.ssm.mapper.StuMapper;
import xx.ssm.mapper.StudentMapper;
import xx.ssm.pojo.Student;
import xx.ssm.service.StuService;
import xx.ssm.service.StudentService;

import java.util.List;

/**
 * @author 星星
 * @create 2022-11-27 10:57
 */
@Service
@Transactional
public class StuServiceImpl implements StuService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.getAllStudent();
    }

    @Override
    public PageInfo<Student> getStudentPage(Integer pageNum,String keyword) {
        //开启分页功能
        PageHelper.startPage(pageNum,4);
        //查询所有的学生信息
//        List<Student> list = studentMapper.getAllStudent();
        List<Student> list = studentMapper.getStudentByKeyword(keyword);

        //获取的分页相关数据
        PageInfo<Student> page = new PageInfo<>(list,5); //导航分页个数
        return page;
    }

    @Override
    public Student getStudentById(Integer stuId) {
        //根据id获取学生信息
        Student student = studentMapper.getStudentById(stuId);
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudent(Integer stuId) {
        studentMapper.deleteStudent(stuId);
    }

    @Override
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    @Override
    public List<Student> getStudentByKeyword(String keyword) {
        return studentMapper.getStudentByKeyword(keyword);
    }


}
