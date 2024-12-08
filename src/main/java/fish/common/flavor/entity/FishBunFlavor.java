package fish.common.flavor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "FISH_BUN_FLAVOR")
@Getter
@Entity
@NoArgsConstructor
public class FishBunFlavor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String flavor;

    private String image;
}
