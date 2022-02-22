package com.example.base;

/**
 * @ClassName StreamDemo
 * @Description TODO
 * @Author zhouguodong
 * @Date 2022/2/22 20:03
 * @Version 1.0
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stream demo
 */
public class StreamDemo {

     static class Student{
         private int id;
         private String name;
         private String score;

         public int getId() {
             return id;
         }

         public void setId(int id) {
             this.id = id;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public String getScore() {
             return score;
         }

         public void setScore(String score) {
             this.score = score;
         }

         @Override
         public String toString() {
             return "Student{" +
                     "id=" + id +
                     ", name='" + name + '\'' +
                     ", score='" + score + '\'' +
                     '}';
         }
     }

    public static void main(String[] args) {

         Student s1=new Student();
         s1.setId(1);
         s1.setName("s1");
         s1.setScore("score");
         Student s2 =new Student();
         s2.setId(2);
         s2.setName("s2");
         s2.setScore("score2");
        Student s3 =new Student();
        s3.setId(3);
        s3.setName("s3");
        s3.setScore("score3");
        List<Student> list =new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);

        Map<Integer, String> collect = list.stream().collect(Collectors.toMap(t -> t.getId(), t -> t.getName()));
        Map<Integer, Student> collect1 = list.stream().collect(Collectors.toMap(t -> t.getId(), t -> t));
        System.out.println(collect);
        System.out.println(collect1);


    }
}
