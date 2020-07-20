package entidades;

import enums.Nivel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trabalhador {
    private String nome;
    private Nivel nivel;
    private String departamento;
    private BigDecimal salarioBase;
    private List<Contrato> contratos = new ArrayList<>();

    public Trabalhador(String departamento, String nome, Nivel nivel, BigDecimal salarioBase) {
        this.departamento = departamento;
        this.nome = nome;
        this.nivel = nivel;
        this.salarioBase = salarioBase;
    }

    public void adicionarContrato(Contrato contrato) {
        contratos.add(contrato);
    }

    public String getNome() {
        return nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public List<Contrato> getContratos() {
        return Collections.unmodifiableList(contratos);
    }
}
