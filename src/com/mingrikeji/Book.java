package com.mingrikeji;




/**
 * Book generated by MyEclipse - Hibernate Tools
 */

public class Book  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private String bookid;
     private Integer price;


    // Constructors

    /** default constructor */
    public Book() {
    }

    
    /** full constructor */
    public Book(String name, String bookid, Integer price) {
        this.name = name;
        this.bookid = bookid;
        this.price = price;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getBookid() {
        return this.bookid;
    }
    
    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public Integer getPrice() {
        return this.price;
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
   








}