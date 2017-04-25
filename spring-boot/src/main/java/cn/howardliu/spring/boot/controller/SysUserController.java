package cn.howardliu.spring.boot.controller;

import cn.howardliu.spring.boot.domain.SysUser;
import cn.howardliu.spring.boot.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <br>created at 17-4-10
 *
 * @author liuxh
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("listAll")
    @ResponseBody
    public List<SysUser> listAll(){
        return sysUserService.listAll();
    }
}
