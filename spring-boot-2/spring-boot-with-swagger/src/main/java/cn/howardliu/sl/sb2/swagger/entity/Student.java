package cn.howardliu.sl.sb2.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * <br>created at 18-6-21
 *
 * @author liuxh
 * @since 0.1.0
 */
@Entity
@ApiModel("学生实体类")
public class Student {
    @ApiModelProperty(notes = "学生编号，不可重复")
    @Id
    @GeneratedValue
    private Integer id;

    @ApiModelProperty(notes = "学生姓名，至少两个字符")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
