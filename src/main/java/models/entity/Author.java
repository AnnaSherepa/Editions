package models.entity;

import java.io.Serializable;
import java.util.List;

public class Author implements Serializable {
    private int id;
    private String nameUk;
    private String nameEn;
    //??
    private List<Edition> editions;

    private Author(){}

    public static class Builder{
        private Author author;
        public Builder(){
            author = new Author();
        }
        public Builder setId(int id){
            author.id = id;
            return this;
        }
        public Builder setNameUk(String name){
            author.nameUk = name;
            return this;
        }
        public Builder setNameEn(String surname){
            author.nameEn = surname;
            return this;
        }

        public Builder setListEdition(List<Edition> ed){
            author.editions = ed;
            return this;
        }
        public Author build(){return author;}
    }


    public List<Edition> getEditions() {
        return editions;
    }
    public int getId() {
        return id;
    }

    public String getNameUk() {
        return nameUk;
    }

    public void setNameUk(String nameUk) {
        this.nameUk = nameUk;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setEditions(List<Edition> editions) {
        this.editions = editions;
    }

    @Override
    public String toString() {
        return nameEn + "[ " + nameUk + " ]";
    }
}
