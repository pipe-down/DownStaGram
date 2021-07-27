package downstagram.downstagram.repository;

import downstagram.downstagram.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("select r from ChatRoom r order by r.roomId desc ")
    List<ChatRoom> chatRoomList();

    Optional<ChatRoom> findByRoomId(String roomId);

}
