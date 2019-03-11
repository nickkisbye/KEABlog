package dk.kea.blog.Models;

public class Blog {

   private int id;
   private String title;
   private String text;
   private int aid;
   private String date;
   private String author;

   public Blog() {

   }

    public Blog(int id, String title, String text, int aid, String date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.aid = aid;
        this.date = date;
    }

    public Blog(int id, String title, String text, String author, String date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.author = author;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
