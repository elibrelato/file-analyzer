package com.mycompany.analyzer.entities;

import java.math.BigDecimal;

public class Venda implements Entity {

	private String idVenda;
	private String idItem;
	private int itemQuantidade;
	private BigDecimal itemPreco;
	private String vendedor;
	
	public Venda() {}
	
	public Venda(String idVenda, String idItem, int itemQuantidade, BigDecimal itemPreco, String vendedor) {
		this.idVenda = idVenda;
		this.idItem = idItem;
		this.itemQuantidade = itemQuantidade;
		this.itemPreco = itemPreco;
		this.vendedor = vendedor;
	}

	public String getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}

	public String getIdItem() {
		return idItem;
	}

	public void setIdItem(String idItem) {
		this.idItem = idItem;
	}

	public int getItemQuantidade() {
		return itemQuantidade;
	}

	public void setItemQuantidade(int itemQuantidade) {
		this.itemQuantidade = itemQuantidade;
	}

	public BigDecimal getItemPreco() {
		return itemPreco;
	}

	public void setItemPreco(BigDecimal itemPreco) {
		this.itemPreco = itemPreco;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	
	public BigDecimal getValorDaVenda() {
		return itemPreco.multiply(new BigDecimal(itemQuantidade));
	}
}
