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
@Table(name = "coordonnees")
public class Coordonnees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "zoom")
    private Long zoom;
    @Column(name = "center1")
    private Double center1;
    @Column(name = "center2")
    private Double center2;
    @Column(name = "country")
    private String country;
    @Column(name = "times_visited")
    private Long timesVisited;
}




