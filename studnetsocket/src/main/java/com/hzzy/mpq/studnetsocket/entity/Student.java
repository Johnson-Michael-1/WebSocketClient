package com.hzzy.mpq.studnetsocket.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * @author:
 * @description:
 * @date: create in
 */
@Data
public class Student {
    private static final long serialVersionUID = 1L;

    private Long studentId;

    private String studentName;

    private String  studentSex;

    private Integer studentAge;

    private String studentClassroom;

    private String studentNumber;

    private String studentCollege;

    private String studentMajor;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enrollDate;

    private String studentPhoto;

    private String studentHobby;

    private String studentStatus;

    @Override
    public String toString() {
        return "SysStudent{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentSex=" + studentSex +
                ", studentAge=" + studentAge +
                ", studentClassroom='" + studentClassroom + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", studentCollege='" + studentCollege + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", enrollDate=" + enrollDate +
                ", studentPhoto=" + studentPhoto +
                ", studentHobby='" + studentHobby + '\'' +
                ", studentStatus=" + studentStatus +
                '}';
    }



    public static long getSerialVesionUID() {
        return serialVersionUID;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentClassroom() {
        return studentClassroom;
    }

    public void setStudentClassroom(String studentClassroom) {
        this.studentClassroom = studentClassroom;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentCollege() {
        return studentCollege;
    }

    public void setStudentCollege(String studentCollege) {
        this.studentCollege = studentCollege;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getStudentPhoto() {
        return studentPhoto;
    }

    public void setStudentPhoto(String studentPhoto) {
        this.studentPhoto = studentPhoto;
    }

    public String getStudentHobby() {
        return studentHobby;
    }

    public void setStudentHobby(String studentHobby) {
        this.studentHobby = studentHobby;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }
}
