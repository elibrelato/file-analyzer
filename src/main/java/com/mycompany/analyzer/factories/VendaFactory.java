package com.mycompany.analyzer.factories;

import java.math.BigDecimal;

import com.mycompany.analyzer.config.Config;
import com.mycompany.analyzer.entities.Venda;

public class VendaFactory {
	
	public Venda getVenda(String dados) {
        Venda venda = null;      
        String node, valor;
        String[] nodes = dados.split(Config.STRING_SPLIT);
        if (nodes.length != Config.VENDA_NODES.size()) { // Safety
            System.out.println("Erro na configuracao da VENDA!");
        } else {
            venda = new Venda();
            for (int i1 = 0; i1 < nodes.length; i1++) {
                node = Config.VENDA_NODES.get(i1);
                valor = nodes[i1];           
                if (node != null) { // Safety
                    switch (node) {
                        case Config.VENDA_STRING_SALE_ID:
                            venda.setIdVenda(valor);
                            break;
                        case Config.VENDA_STRING_ITEM_ID:
                            venda.setIdItem(valor);
                            break;
                        case Config.VENDA_STRING_ITEM_QUANTITY:
                        	try {
                        		venda.setItemQuantidade(Integer.parseInt(valor));
                        	} catch (NumberFormatException e) {
                        		System.out.println(e.getMessage());
							}
                            break;
                        case Config.VENDA_STRING_ITEM_PRICE:
                            try {
                                venda.setItemPreco(new BigDecimal(valor));
                            } catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case Config.VENDA_STRING_SALESMAN_NAME:
                            venda.setVendedor(valor);
                            break;
                    }
                }
            } 
        }
        return venda;
    }
}
