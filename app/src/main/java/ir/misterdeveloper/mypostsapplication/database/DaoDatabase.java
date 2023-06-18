package ir.misterdeveloper.mypostsapplication.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoDatabase {



    @Insert
    Long insertFavoritePost(Favorite favorite);

    @Query("select * from  tbl_favorite ")
    List<Favorite> getFavoritePost();

    @Query("DELETE FROM tbl_favorite WHERE id = :id")
    void deleteFavoriteById(int id);

    @Query("DELETE FROM tbl_favorite WHERE idPost = :idPost")
    void deleteFavoriteByIdPost(int idPost);

    @Query("SELECT EXISTS(SELECT * FROM tbl_favorite WHERE idPost=:idPost)")
    boolean isExists(int idPost);

}