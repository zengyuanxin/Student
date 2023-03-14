//package xx.ssm.controller;
//
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import xx.ssm.pojo.Student;
//import xx.ssm.service.StudentService;
//import xx.ssm.service.StudentService1;
//
//import java.util.List;
//
///**
// * @author 星星
// * @create 2022-11-27 10:56
// */
//public class StudentController1 {
//
//    @Autowired
//    private StudentService1 studentService1;
//
//    /**
//     * 进入添加学生信息的页面
//     * @param student
//     */
//    @RequestMapping(value = "/student/addPage",method = RequestMethod.GET)
//    public String addStudentPage(Student student){
//        return "add";
//    }
//    /**
//     * 添加学生信息
//     * @param student
//     */
//    @RequestMapping(value = "/student/add",method = RequestMethod.POST)
//    public String addStudent(Student student){
//        //修改学生的信息
//        studentService1.addStudent(student);
//        return "redirect:/student/page/1";
//    }
//    /**
//     * 删除学生信息
//     * @param stuId
//     */
//    @RequestMapping(value = "/student/delete/{stuId}",method = RequestMethod.GET)
//    public String deleteStudent(@PathVariable("stuId") Integer stuId, Model model){
//        //根据id获取单个学生的信息
//        studentService1.deleteStudent(stuId);
//        //跳转到student_update.html
//        return "redirect:/student/page/1";
//    }
//    /**
//     * 修改学生信息
//     * @param student
//     * @return
//     */
//    @RequestMapping(value = "/student/update",method = RequestMethod.POST)
//    public String updateStudent(Student student){
//        //修改学生的信息
//        studentService1.updateStudent(student);
//        return "redirect:/student/page/1";
//
//    }
//    /**
//     * 根据id查询学生信息
//     * @param stuId
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/student/edit/{stuId}",method = RequestMethod.GET)
//    public String getStudentById(@PathVariable("stuId") Integer stuId,Model model){
//        //根据id获取单个学生的信息
//        Student student = studentService1.getStudentById(stuId);
//        //将学生信息共享到请求域
//        model.addAttribute("student",student);
//        //跳转到student_update.html
//        return "edit";
//    }
//
//    /**
//     * 分页显示学生列表
//     * @param pageNum
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/student/page/{pageNum}",method = {RequestMethod.POST,RequestMethod.GET})
//    public String getStudentPage(@PathVariable("pageNum") Integer pageNum, Model model, String keyword) {
//        if (keyword == null){
//            keyword = "";
//        }
//        //获取学生的分页信息
//        PageInfo<Student> page = studentService1.getStudentPage(pageNum,keyword);
//        //将分页数据共享到请求域
//        model.addAttribute("page", page);
//        //跳转到student_list.html
//        return "student_list";
//    }
//    /**
//     * 显示全部学生列表
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/student",method = RequestMethod.GET)
//    public String getAllStudent(Model model){
//        //查询所有的学生信息
//        List<Student> list = studentService1.getAllStudent();
//        //将学生信息在请求域中共享
//        model.addAttribute("list",list);
//        //跳转到student_list.html
//        return "student_list";
//    }
//}
