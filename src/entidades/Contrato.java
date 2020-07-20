package entidades;

import java.math.BigDecimal;
import java.util.Date;

public class Contrato {
    private Date data;
    private BigDecimal valorPorHora;
    private Integer duracaoEmHoras;

    public Contrato(Date data, BigDecimal valorPorHora, Integer duracaoEmHoras) {
        this.data = data;
        this.valorPorHora = valorPorHora;
        this.duracaoEmHoras = duracaoEmHoras;
    }

    public Date getData() {
        return data;
    }

    public BigDecimal getValorPorHora() {
        return valorPorHora;
    }

    public Integer getDuracaoEmHoras() {
        return duracaoEmHoras;
    }
}
