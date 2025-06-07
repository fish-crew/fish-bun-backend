package fish.domain.flavor.index;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "BUNG_FLAVOR")
@Getter
@Entity
@NoArgsConstructor
public class BungFlavorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String flavor;
    private String iconCode;
    private int seq;
    @CreationTimestamp
    private LocalDateTime regDate;
    private String description;
    private String highlight;
}
