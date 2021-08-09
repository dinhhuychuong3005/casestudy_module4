package com.example.casestudymodule4.service.friend;

import com.example.casestudymodule4.model.entity.Friend;
import com.example.casestudymodule4.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFriendService extends IGeneralService<Friend> {
    @Query("SELECT f FROM Friend f inner join  f.user u where u.id=:id")
    public List<Friend> findAllFriendById(@Param("id") Long id);

    @Query("SELECT f FROM Friend f where f.id=:idF and f.idFriendOfUser=:idU")
    public Friend findFriendByIdUser(@Param("idF") Long idF,@Param("idU") Long idU);

    public Friend findByUserIdAndIdFriendOfUser(Long userId,Long idUserFriend);
}
