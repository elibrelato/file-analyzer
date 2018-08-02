package com.mycompany.analyzer.entities;

import java.math.BigDecimal;

public class Vendedor implements Entity {
	
	private String nome;
    private String cpf;
    private BigDecimal salario;
    
    public Vendedor() {}
    
    public Vendedor(String nome, String cpf, BigDecimal salario) {
    	this.nome = nome;
    	this.cpf = cpf;
    	this.salario = salario;
    }
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
}
