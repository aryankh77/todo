package com.example.home.todo_ap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

class User implements Serializable {
    private static final long serialVersionUID=2222;
    private String username;
    private String password;
    private String name;
    private String familyName;
    private String email;
    private boolean isLogin;
    private Type Type;
    private ArrayList<TaskInfo> tasks;


    public User(String username, String password, String name, String familyName, String email, Type Type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.Type = Type;
        tasks = new ArrayList<>();
    }


    public void deleteTask(TaskInfo task) throws DeletException{
        if (tasks.contains(task)){
            tasks.remove(task);}
        else {
            throw new DeletException();
        }

    }


    public void addTask(TaskInfo task){
        tasks.add(task);
    }

    public boolean getIsLogin() {
        return isLogin;
    }

    public void setType(Type type) {
        this.Type = type;
    }

    public Type getType() {
        return Type;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setTasks(ArrayList<TaskInfo> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<TaskInfo> getTasks() {

        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
