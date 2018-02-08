package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * @author by hj on 2018-1-25.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name", nullable = true, length = 50 )
    private String name;

    @Column(name = "username", nullable = true, length = 50)
    private String username;

    @Min(value = 18 ,message = "未成年人禁止注册！")
    private Integer age;

    @Column(name = "password", nullable = true, length = 50)
    private String password;

    public User() {
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
