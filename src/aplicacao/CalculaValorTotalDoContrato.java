package aplicacao;

import entidades.Contrato;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculaValorTotalDoContrato {
    private Contrato contrato;

    public CalculaValorTotalDoContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public BigDecimal calcular() {
        return contrato.getValorPorHora().multiply(new BigDecimal(contrato.getDuracaoEmHoras())).setScale(2, RoundingMode.HALF_EVEN);
    }
}
