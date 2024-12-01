package fish.common.fishBun.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "fish_bun")
@Getter
@Entity
@NoArgsConstructor
public class FishBun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String flavor;

    private String image;
}
