package com.mycompany.analyzer.factories;

import com.mycompany.analyzer.config.Config;
import com.mycompany.analyzer.entities.Cliente;

public class ClienteFactory {

	public Cliente getCliente(String dados) {
		Cliente cliente = null;
		String node, valor;
		String[] nodes = dados.split(Config.STRING_SPLIT);
        if (nodes.length != Config.CLIENTE_NODES.size()) { // Safety
            System.out.println("Erro na configuracao do CLIENTE!");
        } else {
            cliente = new Cliente();
            for (int i1 = 0; i1 < nodes.length; i1++) {
                node = Config.CLIENTE_NODES.get(i1);
                valor = nodes[i1];
                if (node != null) { // Safety
                    switch (node) {
                        case Config.CLIENTE_STRING_CNPJ:
                            cliente.setCnpj(valor);
                            break;
                        case Config.CLIENTE_STRING_NAME:
                            cliente.setNome(valor);
                            break;
                        case Config.CLIENTE_STRING_BUSINESS_AREA:
                            cliente.setRamo(valor);
                            break;
                    }
                }
            }
        }
        return cliente;
	}
}
