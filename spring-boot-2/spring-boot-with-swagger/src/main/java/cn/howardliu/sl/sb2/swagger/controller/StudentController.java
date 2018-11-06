package cn.howardliu.sl.sb2.swagger.controller;

import cn.howardliu.sl.sb2.swagger.dao.StudentRepository;
import cn.howardliu.sl.sb2.swagger.entity.Response;
import cn.howardliu.sl.sb2.swagger.entity.Student;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * <br>created at 18-6-21
 *
 * @author liuxh
 * @since 0.1.0
 */
@RestController
@Api("学生信息管理操作")
public class StudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    @ApiOperation(value = "查询学生列表", notes = "查询全部学生信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功")
    })
    public List<Student> retrieveAllStudents() {
        return studentRepository.findAll();
    }

    @DeleteMapping("/students/{id}")
    @ApiOperation(value = "删除学生信息", notes = "根据学生id删除学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "待删除学生编号")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功")
    })
    public void deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
    }

    @PostMapping("/students")
    @ApiOperation(value = "新增学生信息", notes = "根据输入数据新增会员信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功")
    })
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/students/{id}")
    @ApiOperation(value = "修改学生信息", notes = "根据学生id修改对应会员信息，如果id对应学生不存在，返回")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "待修改学生编号，如果不存在，返回")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 400, message = "参数异常：学生编号不存在")
    })
    public Response<Student> updateStudent(@RequestBody Student student, @PathVariable int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (!studentOptional.isPresent()) {
            return new Response<Student>().setCode(400).setDesc("学生编号不存在");
        }
        student.setId(id);
        studentRepository.save(student);
        return new Response<Student>().setCode(200).setDesc("成功").setData(student);
    }
}
