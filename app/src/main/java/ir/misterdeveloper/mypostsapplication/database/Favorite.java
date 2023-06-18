package ir.misterdeveloper.mypostsapplication.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_favorite")
public class Favorite {


    public Favorite() {
    }



    public Favorite(String titlePost, int postId,int idPost) {

        this.titlePost = titlePost;
        this.postId = postId;
        this.idPost = idPost;
    }


    @Ignore
    public Favorite(int id, String titlePost, int postId,int idPost) {
        this.id = id;
        this.titlePost = titlePost;
        this.postId = postId;
        this.idPost = idPost;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;


    @ColumnInfo(name = "titlePost")
    private String titlePost;

    @ColumnInfo(name = "postId")
    private int postId;

    @ColumnInfo(name = "idPost")
    private int idPost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }
}