package fish.common.fishBun.entity;

import fish.common.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "fish_bun")
@Getter
@Entity
@NoArgsConstructor
public class UserFishBunBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String completed_flavor;
}
