package fish.common.fishBun.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "fish_bun_book")
@Getter
@Entity
@NoArgsConstructor
public class FishBunBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
}
