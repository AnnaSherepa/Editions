package models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Edition implements Serializable {
    private int id;
    private String titleUk;
    private String titleEn;
    private String descriptionUk;
    private String descriptionEn;

    private  String imgPath;
    private int idGenre;
    private int idAuthor;

    private BigDecimal price;
    private String measurement;
    private Author author;
    private Genre genre;

    public static class Builder{
        private Edition edition;
        public Builder(){ edition = new Edition(); }
        public Builder setId(int id){
            edition.id = id;
            return this;
        }
        public Builder setTitleUk(String title){
            edition.titleUk = title;
            return this;
        }
        public Builder setTitleEn(String title){
            edition.titleEn = title;
            return this;
        }
        public Builder setDescriptionUk(String description){
            edition.descriptionUk = description;
            return this;
        }

        public Builder setDescriptionEn(String description){
            edition.descriptionEn = description;
            return this;
        }

        public Builder setImgPath(String img){
            edition.imgPath = img;
           return this;
        }
        public Builder setIdGenre(int id){
            edition.idGenre = id;
            return this;
        }
        public Builder setIdAuthor(int id){
            edition.idAuthor = id;
            return this;
        }
        public Builder setPrice(BigDecimal price){
            edition.price = price;
            return this;
        }
        public Builder setMeasurement(String measurement){
            edition.measurement = measurement;
            return this;
        }

        public Edition build(){ return edition; }
    }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    public int getId() {
        return id;
    }
    public int getIdGenre() {
        return idGenre;
    }
    public String getTitleUk() {
        return titleUk;
    }
    public String getTitleEn() {
        return titleEn;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public String getImgPath() {
        return imgPath;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public String getDescriptionUk() {
        return descriptionUk;
    }
    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setTitleUk(String title) {
        this.titleUk = title;
    }
    public void setTitleEn(String title) {
        this.titleEn = title;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void setDescriptionUk(String description) {
        this.descriptionUk = description;
    }
    public void setDescriptionEn(String description) {
        this.descriptionEn = description;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Edition{" +
                "id=" + id +
                ", titleUk='" + titleUk + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", descriptionUk='" + descriptionUk + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", idGenre=" + idGenre +
                ", price=" + price +
                ", measurement='" + measurement + '\'' +
                ", authors=" + author +
                ", genre=" + genre +
                '}';
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }
}
