package com.taiquan.bean;

import com.taiquan.domain.customer.Contacts;
import com.taiquan.domain.customer.Employee;
import com.taiquan.domain.customerEnums.PositionType;
import java.util.*;


public class EmployeeBean {
    private int employeeId;
    private String         name;
    private boolean        gendarMale;
    private String         age;
    private PositionType   positionType;
    private List<Contacts> contactes;
    private String         email;
    private String others;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGendarMale() {
        return gendarMale;
    }

    public void setGendarMale(boolean gendarMale) {
        this.gendarMale = gendarMale;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public List<Contacts> getContactes() {
        return contactes;
    }

    public void setContactes(List<Contacts> contactes) {
        this.contactes = contactes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public static Employee toEmployee(EmployeeBean eb){
        Employee employee = new Employee();
        employee.setName(eb.getName());
        employee.setAge(eb.getAge());
        employee.setContacts(new ArrayList<>(eb.getContactes()));
        employee.setEmail(eb.getEmail());
        employee.setGendarMale(eb.isGendarMale());
        employee.setPositionType(eb.getPositionType());
        employee.setOthers(eb.getOthers());
        return employee;
    }

    @Override
    public String toString() {
        return "EmployeeBean{" + "employeeId=" + employeeId + ", name='" + name + '\'' + ", gendarMale=" + gendarMale + ", age='" + age + '\'' + ", positionType=" + positionType + ", contactes=" + contactes + ", email='" + email + '\'' + ", others='" + others + '\'' + '}';
    }


  /*public  static void main(String[] args){
        EmployeeBean employeeBean = getEmployeeBean();
        System.out.println(employeeBean);
    }*/

}
