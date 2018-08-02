package com.mycompany.analyzer.factories;

import java.math.BigDecimal;

import com.mycompany.analyzer.config.Config;
import com.mycompany.analyzer.entities.Vendedor;

public class VendedorFactory {

	public Vendedor getVendedor(String dados) {
        Vendedor vendedor = null;
        String node, valor;
        String[] nodes = dados.split(Config.STRING_SPLIT);
        if (nodes.length != Config.VENDEDOR_NODES.size()) { // Safety
            System.out.println("Erro na configuracao do VENDEDOR!");
        } else {
            vendedor = new Vendedor();
            for (int i1 = 0; i1 < nodes.length; i1++) {
                node = Config.VENDEDOR_NODES.get(i1);
                valor = nodes[i1];
                if (node != null) { // Safety
                    switch (node) {
                        case Config.VENDEDOR_STRING_CPF:
                            vendedor.setCpf(valor);
                            break;
                        case Config.VENDEDOR_STRING_NAME:
                            vendedor.setNome(valor);
                            break;
                        case Config.VENDEDOR_STRING_SALARY:    
                            try {
                                vendedor.setSalario(new BigDecimal(valor));
                            } catch (NumberFormatException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                    }
                }
            }
        }
        return vendedor;
    }
}
