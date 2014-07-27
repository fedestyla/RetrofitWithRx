package it.federico.com.retrofitrx.Model;


import com.google.gson.annotations.SerializedName;

public class Repository {

    // Created only some params
    public long id;
    public String name;
    @SerializedName("full_name")
    public String fullName;
    public Owner owner;

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", owner=" + owner +
                '}';
    }
}
