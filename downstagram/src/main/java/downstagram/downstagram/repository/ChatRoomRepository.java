package downstagram.downstagram.repository;

import downstagram.downstagram.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatMessage, Long> {
}
