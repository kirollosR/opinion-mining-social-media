package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "post_images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "image_id")
    int id;
    @Column(name = "url")
    String url;
    @Column(name = "name")
    String name;
    @Column(name = "size")
    double size;

    public Image(String url, String name, int id, double size) {
        this.url = url;
        this.name = name;
        this.id = id;
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
