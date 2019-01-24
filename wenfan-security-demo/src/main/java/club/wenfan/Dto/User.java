package club.wenfan.Dto;

import club.wenfan.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author:wenfan
 * @description:
 * @data: 2018/12/11 22:28
 */
public class User {


    // 使用接口指定用户视图
    public interface UserSimpeView{}

    public interface UserDetailView extends UserSimpeView{}

    private Integer age;

    private Integer salary;

    @MyConstraint(message ="this is test")
    private String username;

    @NotBlank   //不为空 与@Valid 注解使用
    private String password;


    @JsonView(UserSimpeView.class) //
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpeView.class)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "用户名："+username+"密码："+password;
    }
}
