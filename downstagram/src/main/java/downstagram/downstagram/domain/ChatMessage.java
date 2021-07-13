package downstagram.downstagram.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatRoomId")
    private Long id;

    private String receiver;
    private String writer;
    private String message;

    @Enumerated(EnumType.STRING)
    private MessageType type; // ENTER,CHAT,LEAVE
}
