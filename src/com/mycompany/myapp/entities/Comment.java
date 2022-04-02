/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Extreme PC
 */
public class Comment {
    int id;
    int author_id;
    int blog_id;
    String comment;
    Date date;

    public Comment(int id, int author_id, int blog_id, String comment, Date date) {
        this.id = id;
        this.author_id = author_id;
        this.blog_id = blog_id;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", author_id=" + author_id + ", blog_id=" + blog_id + ", comment=" + comment + ", date=" + date + '}';
    }

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(int blog_id) {
        this.blog_id = blog_id;
    }
    
}
