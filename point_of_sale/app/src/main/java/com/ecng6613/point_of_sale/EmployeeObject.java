package com.ecng6613.point_of_sale;

/**
 * Created by Daniel Phillips on 5/22/2017.
 */

public class EmployeeObject {
    private String employee_id;
    private String password_hash;
    private String total_hours_work;
    private String last_login;
    private String date_employeed;
    private String first_name;
    private String last_name;
    private String employee_image;
    private String employee_position;

    EmployeeObject(String employee_id,
                   String password_hash,
                   String total_hours_work,
                   String  last_login,
                   String date_employeed,
                   String first_name,
                   String last_name,
                   String employee_image,
                   String employee_position){

        this.employee_id = employee_id;
        this.password_hash=password_hash;
        this.total_hours_work = total_hours_work;
        this.last_login = last_login;
        this.date_employeed = date_employeed;
        this.first_name = first_name;
        this.last_name = last_name;
        this.employee_image = employee_image;
        this.employee_position = employee_position;
    }

    public String getDate_employeed() {
        return date_employeed;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public String getEmployee_image() {
        return employee_image;
    }

    public String getEmployee_position() {
        return employee_position;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_login() {
        return last_login;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getTotal_hours_work() {
        return total_hours_work;
    }
}
