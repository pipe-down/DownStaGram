package downstagram.downstagram.service;

import downstagram.downstagram.domain.ChatRoom;
import downstagram.downstagram.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.chatRoomList();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findByRoomId(roomId).orElse(null);
    }

    @Transactional
    public ChatRoom save(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }
}
