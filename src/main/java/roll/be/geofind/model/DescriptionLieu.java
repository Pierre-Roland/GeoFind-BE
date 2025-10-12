package roll.be.geofind.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "description_lieu")
public class DescriptionLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lieu", nullable = false)
    private String lieu;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
}
