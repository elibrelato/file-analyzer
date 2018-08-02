package com.mycompany.analyzer.factories;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mycompany.analyzer.config.Config;
import com.mycompany.analyzer.entities.Venda;

public class VendaFactoryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Config config = new Config();
        config.processa_Config();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetVenda() {
		String dados = "003;10;11010;300;3403.30;Diego";
        Venda venda = new VendaFactory().getVenda(dados);
        assertEquals("10", venda.getIdVenda());
        assertEquals("11010", venda.getIdItem());
        assertEquals(300, venda.getItemQuantidade());
        assertEquals(new BigDecimal("3403.30"), venda.getItemPreco());
        assertEquals("Diego", venda.getVendedor());
        assertEquals(new BigDecimal("3403.30").multiply(new BigDecimal("300")), venda.getValorDaVenda());
	}
}
