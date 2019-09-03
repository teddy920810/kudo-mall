package org.linlinjava.litemall.admin.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Objects;

public class ExcelModel {
    @Excel(name = "课程名称")
    private String courseName;
    @Excel(name = "性质")
    private String require;
    @Excel(name = "学时")
    private String classHour;
    @Excel(name = "学分")
    private String credit;
    @Excel(name = "书名")
    private String bookName;
    @Excel(name = "编者")
    private String bookAuthor;
    @Excel(name = "出版社及版次")
    private String publisher;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "任课老师")
    private String teacher;
    @Excel(name = "具体上课时间")
    private String content;
    @Excel(name = "开课班级")
    private String className;
    @Excel(name = "教室")
    private String classRoom;

    public ExcelModel() {
    }

    public ExcelModel(String courseName, String require, String classHour, String credit, String bookName, String bookAuthor, String publisher, String remark, String teacher, String content, String className, String classRoom) {
        this.courseName = courseName;
        this.require = require;
        this.classHour = classHour;
        this.credit = credit;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.publisher = publisher;
        this.remark = remark;
        this.teacher = teacher;
        this.content = content;
        this.className = className;
        this.classRoom = classRoom;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    public String getClassHour() {
        return classHour;
    }

    public void setClassHour(String classHour) {
        this.classHour = classHour;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExcelModel that = (ExcelModel) o;
        return Objects.equals(courseName, that.courseName) &&
                Objects.equals(require, that.require) &&
                Objects.equals(classHour, that.classHour) &&
                Objects.equals(credit, that.credit) &&
                Objects.equals(bookName, that.bookName) &&
                Objects.equals(bookAuthor, that.bookAuthor) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(content, that.content) &&
                Objects.equals(className, that.className) &&
                Objects.equals(classRoom, that.classRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, require, classHour, credit, bookName, bookAuthor, publisher, remark, teacher, content, className, classRoom);
    }
}
