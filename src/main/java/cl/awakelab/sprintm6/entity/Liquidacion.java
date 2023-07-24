package cl.awakelab.sprintm6.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table
public class Liquidacion {

    @Id
    @Column(name = "id_liquidacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLiquidacion;

    // Relacion con Trabajador
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_trabajador")
    private Trabajador trabajador;

    @Column(nullable = false)
    private LocalDate periodo; // Se considerará como fecha de emisión o pago

    @Column(name = "sueldo_imponible", nullable = false)
    private int sueldoImponible;

    @Column(name = "sueldo_liquido", nullable = false)
    private int sueldoLiquido;

    // Relacion con Institucion Salud
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_salud")
    private InstitucionSalud institucionSalud;

    @Column(name = "monto_inst_salud", nullable = false)
    private int montoInstSalud;

    // Relacion con Institucion Prevision
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_inst_previsional")
    private InstitucionPrevision institucionPrevision;

    @Column(name = "monto_inst_previsional", nullable = false)
    private int montoInstPrevisional;

    @Column(name = "total_descuento", nullable = false)
    private int totalDescuento;

    @Column(name = "total_haberes", nullable = false)
    private int totalHaberes;

    @Column(nullable = false)
    private int anticipo;

}
