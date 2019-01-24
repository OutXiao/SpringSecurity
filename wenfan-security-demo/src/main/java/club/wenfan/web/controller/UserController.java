package club.wenfan.web.controller;

import club.wenfan.Dto.User;
import club.wenfan.ExceptionHandle.CostomerException;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.corba.se.impl.naming.cosnaming.InternalBindingValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/11 22:26
 */
@RestController
public class UserController {


    /**
     * 拿到已经认证的用户信息
     *
     *   也可以这样写
     * public Authentication getCurrent(Authentication authentication){
     *         return authentication ;
     *     }
     *
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @GetMapping("/me")
    public Authentication getCurrent(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    /**
     * 拿到具体的认证信息
     * @author wenfan
     * @date
     * @param
     * @return
     */
    @GetMapping("/prinicipal")
    public UserDetails getPrincipal(@AuthenticationPrincipal UserDetails user){
        return user;
    }


    @GetMapping("/user")
    @JsonView(User.UserSimpeView.class) //controller中使用视图  只显示username
    public List<User> query(@RequestParam(required = false) String username){

        List<User> list=new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }

    //正则表达式 ，只能传入数字
    @GetMapping(value = "/user/{id:\\d+}")
    //@JsonView(User.UserDetailView.class) //显示username和password
    public User getinfo(@RequestParam(required = false) String id){
        User user=new User();
        user.setUsername("tom");
        user.setSalary(4000);
        user.setPassword("mima");
        user.setAge(23);
        //throw new  CostomerException(1);
        System.out.println("enter getinfo service");
        return user;

    }

    @PostMapping("/user")
    @JsonView(User.UserDetailView.class)
    public User getJson(@Valid @RequestBody User user, BindingResult errors){

        if(errors.hasErrors()){
                errors.getAllErrors().stream().forEach(error-> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.toString());
        return user;
    }
    @PutMapping("/user")
    @JsonView(User.UserDetailView.class)
    public User update(@Valid @RequestBody User user, BindingResult errors){

        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error-> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.toString());
        return user;
    }

    

}
