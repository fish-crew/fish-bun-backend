package fish.domain.bungbal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "BUNGBAL")
public class Bungbal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String mbti;
    private Long count;

    // Getter, Setter
    public void incrementCount() {
        this.count++;
    }
}
