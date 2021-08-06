package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IFriendRepository extends JpaRepository<Friend,Long> {
    @Query("SELECT f FROM Friend f inner join  f.user u where u.id=:id")
    public List<Friend> findAllFriendById(@Param("id") Long id);

    @Query("SELECT f FROM Friend f where f.id=:idF and f.idFriendOfUser=:idU")
    public Friend findFriendByIdUser(@Param("idF") Long idF,@Param("idU") Long idU);

    public Friend findByUserIdAndIdFriendOfUser(Long userId,Long idUserFriend);
}
