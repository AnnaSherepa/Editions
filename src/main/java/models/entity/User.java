package models.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    /*May I hate to add setting just one object of editions?*/
    private int id;
    private String name;
    private String surname;

    private String login;
    private String pass;
    private String email;

    private String role;
    private int status;
    private BigDecimal balance;

    private List<Edition> editions;
    public static class Builder{
        private User user;
        public Builder(){
            user = new User();
        }

        public Builder setId(int id) {
            user.id = id;
            return this;
        }
        public Builder setName(String name) {
            user.name = name;
            return this;
        }
        public Builder setSurname(String surname) {
            user.surname = surname;
            return this;
        }

        public Builder setStatus(int status) {
            user.status = status;
            return this;
        }
        public Builder setLogin(String login){
            user.login = login;
            return this;
        }
        public Builder setPass(String pass){
            user.pass = pass;
            return this;
        }
        public Builder setEmail(String email){
            user.email = email;
            return this;
        }
        public Builder setRole(String role){
            user.role = role;
            return this;
        }

        public Builder setBalance(BigDecimal balance){
            user.balance = balance;
            return this;
        }
        public Builder setEditions(List<Edition> editions){
            user.editions = editions;
            return this;
        }
        public User build(){
            if(user.editions == null)
                user.editions = new ArrayList<>();
            return user;
        }
    }
    private User(){ }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public String getLogin() { return login; }
    public String getPass() {
        return pass;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }
    public List<Edition> getEditions() {
        return editions;
    }

    public void setEditions(List<Edition> editions) {
        this.editions = editions;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean existInUserEditions(int id){
        Edition checked = editions.stream().filter(edition1 -> id  == edition1.getId()).findAny().orElse(null);
        return checked != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", balance=" + balance +
                ", editions=" + editions +
                '}';
    }
}
