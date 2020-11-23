package com.example.curdoperationassignment.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.curdoperationassignment.db.entity.UserDetails;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserDetails userDetails);

    @Query("SELECT * FROM userdetails")
    List<UserDetails> getAll();

    @Query("update UserDetails SET full_Name = :name,Address = :address,email = :email,City= :city,State= :State,Country = :Country,zip_Code = :zipCode,phone_No = :phoneNo,mobile_No= :mobileNo where id =:id")
    void update(String name, String address, String email, String city, String State, String Country, String zipCode, String phoneNo, String mobileNo, int id);

    @Query("delete from UserDetails where id = :id")
    void delete(int id);
}
