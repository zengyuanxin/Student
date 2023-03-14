package xx.ssm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Constants;
import com.mysql.cj.Session;
import org.apache.ibatis.cache.CacheKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xx.ssm.pojo.Student;
import xx.ssm.service.StudentService;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author 星星
 * @create 2022-11-22 23:34
 *  * 查询所有的学生信息-->/student/-->get
 *  * 查询学生的分页信息-->/student/page/1-->get
 *  * 根据id查询学生信息-->/student/1-->get
 *  * 跳转到添加页面-->/to/add-->get
 *  * 添加员工信息-->/student/add-->post
 *  * 修改学生信息-->/student/edit-->get
 *  * 删除学生信息-->/student/1-->get
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 进入添加学生信息的页面
     * @param student
     */
    @RequestMapping(value = "/student/addPage",method = RequestMethod.GET)
    public String addStudentPage(Student student){
        return "add";
    }
    /**
     * 添加学生信息，上传文件（头像）
     * @param student
     */
    @RequestMapping(value = "/student/add",method = {RequestMethod.POST,RequestMethod.GET})
    public String addStudent(Student student,MultipartFile photo, HttpSession session) throws IOException {
        //获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        //获取上传的文件的后缀名
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        //获取uuid
        String uuid = UUID.randomUUID().toString();
        //拼接一个新的文件名
        fileName = uuid + hzName;
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取当前工程下photo目录的真实路径
        String photoPath = servletContext.getRealPath("photo");
        //创建photoPath所对应的File对象
        File file = new File(photoPath);
        //判断file所对应目录是否存在
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        //上传文件
        photo.transferTo(new File(finalPath));
        //修改学生的信息
        student.setPIC(finalPath);
        studentService.addStudent(student);
        if ("admin".equals(student.getNICK())){
            return "redirect:/student/page/1";
        }else{
            return "redirect:/student/edit/" + student.getSTUNO();
        }

    }
    /**
     * 删除学生信息
     * @param stuId
     */
    @RequestMapping(value = "/student/delete/{stuId}",method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("stuId") Integer stuId,Model model){
        //根据id删除学生信息
        studentService.deleteStudent(stuId);
        //跳转到student_update.html
        return "redirect:/student/page/1";
    }
    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @RequestMapping(value = "/student/update",method = {RequestMethod.POST,RequestMethod.GET})
    public String updateStudent(Student student){
        //修改学生的信息
        studentService.updateStudent(student);
        return "edit";

    }
    /**
     * 根据id查询学生信息
     * @param stuId
     * @param model
     * @return
     */
    @RequestMapping(value = "/student/edit/{stuId}",method = RequestMethod.GET)
    public String getStudentById(@PathVariable("stuId") Integer stuId,Model model){
        //根据id获取单个学生的信息
        Student student = studentService.getStudentById(stuId);
        //将学生信息共享到请求域
        model.addAttribute("student",student);
        //跳转到student_update.html
        return "edit";
    }

    /**
     * 分页显示学生列表
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "/student/page/{pageNum}",method = {RequestMethod.POST,RequestMethod.GET})
    public String getStudentPage(@PathVariable("pageNum") Integer pageNum,Model model,String keyword) {
        if (keyword == null){
            keyword = "";
        }
        //获取学生的分页信息
        PageInfo<Student> page = studentService.getStudentPage(pageNum,keyword);
        //将分页数据共享到请求域
        model.addAttribute("page", page);
        //跳转到student_list.html
        return "admin_index";
//        return "student_list";
    }
    @RequestMapping( value = "/student/search/{pageNum}",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public  List<Student> search(@PathVariable("pageNum") Integer pageNum, Model model, String keyword) {
        if (keyword == null){
            keyword = "";
        }
        //获取学生的分页信息
        PageInfo<Student> page = studentService.getStudentPage(pageNum,keyword);
        //将分页数据共享到请求域
        model.addAttribute("page", page);
        return page.getList();
    }

    /**
     * 显示全部学生列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public String getAllStudent(Model model){
        //查询所有的学生信息
        List<Student> list = studentService.getAllStudent();
        //将学生信息在请求域中共享
        model.addAttribute("list",list);
        //跳转到student_list.html
        return "student_list";
    }

    /**
     * 下载文件
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/test/down")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        //就是获取某个资源在服务器上的路径
        String realPath = servletContext.getRealPath("img");
        realPath = realPath + File.separator + "1.png";
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组，is.available()获取输入流所对应文件的字节数
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=1.png");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        //bytes：响应体
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }

    /**
     * 登陆功能
     * @param NICK
     * @param PASSWORD
     * @return
     */
    @RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(String NICK,String PASSWORD,String verifyCode,HttpServletRequest httpServletRequest) {
        String trueCode = (String) httpServletRequest.getSession().getAttribute("verifyCode");
        if (trueCode.equals(verifyCode)){
            Student student = studentService.login(NICK,PASSWORD);
            if (student!=null){
                if ("admin".equals(NICK)){
                    return "redirect:/student/page/1";
                }else{
                    return "redirect:/student/edit/" + student.getSTUNO();
                }
            }else{
                return "login";
            }
        }
        return "404";
    }

    @RequestMapping(value = "/regist",method = {RequestMethod.POST,RequestMethod.GET})
    public String regist() {
       return "regist";
    }
}
