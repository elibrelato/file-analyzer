package com.mycompany.analyzer.config;

public interface Configs {
	
	public static final String CAMPO_SEPARADOR = "separador";
	
	public static final String CAMPO_VENDEDOR = "vendedor";
    public static final String CAMPO_CLIENTE = "cliente";
    public static final String CAMPO_VENDA = "venda";

    public static final String CAMPO_ID = "id";
    public static final String CAMPO_FORMATO = "formato";
    
    // dados do vendedor
    public static final String VENDEDOR_STRING_CPF = "cpf";
    public static final String VENDEDOR_STRING_NAME = "nome";
    public static final String VENDEDOR_STRING_SALARY = "salario";
    
    // dados do cliente
    public static final String CLIENTE_STRING_CNPJ = "cnpj";
    public static final String CLIENTE_STRING_NAME = "nome";
    public static final String CLIENTE_STRING_BUSINESS_AREA = "areadeatuacao";
    
    // dados da venda
    public static final String VENDA_STRING_SALE_ID = "idvenda";
    public static final String VENDA_STRING_ITEM_ID = "iditem";
    public static final String VENDA_STRING_ITEM_QUANTITY = "itemquantidade";
    public static final String VENDA_STRING_ITEM_PRICE = "itempreco";
    public static final String VENDA_STRING_SALESMAN_NAME = "vendedor";
}
