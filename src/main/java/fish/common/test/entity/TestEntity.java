package fish.common.test.entity;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "test")
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestEntity {
    @Id
    private String id;
    private String name;
}
