package org.example.models;

import jakarta.persistence.*;

import java.util.Random;
@Entity
@Table(name = "courses")
public class Course {

    private static final String[] titles = new String[]{
            "Математика",
            "Иностранный язык",
            "Теоретическая механика",
            "Сопротивление материалов",
            "История",
            "Физика"
    };

    private static final Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int duration;

    public static Course createCourse(){
        return new Course(titles[random.nextInt(titles.length)], random.nextInt(60, 100));
    }

    public Course(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }

    public void updateTitle(){
        title = "test";
    }

    public void updateDuration(){
        duration = 0;
    }



}
