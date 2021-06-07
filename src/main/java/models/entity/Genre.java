package models.entity;

import java.io.Serializable;
import java.util.Objects;

public class Genre implements Serializable {
    private int id;
    private String nameUk;
    private String nameEn;

    private Genre() { }

    public static class Builder{
        private Genre genre;
        public Builder(){ genre = new Genre(); }
        public Builder setId(int id){
            genre.id = id;
            return this;
        }
        public Builder setNameEn(String nameEn){
            genre.nameEn = nameEn;
            return this;
        }
        public Builder setNameUk(String nameUk){
            genre.nameUk = nameUk;
            return this;
        }

        public Genre build(){ return genre; }
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return nameUk.equals(genre.nameUk) &&
                nameEn.equals(genre.nameEn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameUk, nameEn);
    }

    @Override
    public String toString() {
        return nameEn + "[ " +  nameUk + " ]";
    }
}
