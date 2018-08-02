package com.mycompany.analyzer.dados;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.analyzer.entities.Cliente;
import com.mycompany.analyzer.entities.Entity;
import com.mycompany.analyzer.entities.Venda;
import com.mycompany.analyzer.entities.Vendedor;

public class Dados {
	
	private static List<Vendedor> vendedores = new ArrayList<>();
	private static List<Cliente> clientes = new ArrayList<>();
	private static List<Venda> vendas = new ArrayList<>();
	
	public static void clear() {
		vendedores.clear();
		clientes.clear();
		vendas.clear();
	}
	
	public static void salvar(Entity entity) {
		if (entity instanceof Vendedor) {
			vendedores.add((Vendedor) entity);
		} else if (entity instanceof Cliente) {
			clientes.add((Cliente) entity);
		} else if (entity instanceof Venda) {
			vendas.add((Venda) entity);
		}
	}
	
	public static List<Vendedor> getVendedores() {
		return vendedores;
	}
	
	public static List<Cliente> getClientes() {
		return clientes;
	}
	
	public static List<Venda> getVendas() {
		return vendas;
	}
}
