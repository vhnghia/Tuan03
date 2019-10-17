package com.example.qltv;

public class TacGia {
    private String id;
    private String author;

    public TacGia(){
        super();
    }

    public TacGia(String id, String author) {
        this.id = id;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return  this.id +"---" + this.author;
    }
}

