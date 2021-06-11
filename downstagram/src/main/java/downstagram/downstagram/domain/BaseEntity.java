package downstagram.downstagram.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity {
    @CreatedBy
    @Column(updatable = false)
    private String createBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @Override
    public void prePersist() {
        super.prePersist();
        createBy = "TEST1"; // 추후 세션 데이터로 변환
        lastModifiedBy = "TEST2";
    }

    @Override
    public void preUpdate() {
        super.preUpdate();
        lastModifiedBy = "TEST3";
    }
}
