package com.mycompany.analyzer.factories;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mycompany.analyzer.config.Config;
import com.mycompany.analyzer.entities.Vendedor;

public class VendedorFactoryTest {

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
	public void testGetVendedor() {
		String dados = "001;1234567891234;Diego;5000.00";
        Vendedor vendedor = new VendedorFactory().getVendedor(dados);
        assertEquals("Diego", vendedor.getNome());
        assertEquals("1234567891234", vendedor.getCpf());
        assertEquals(new BigDecimal("5000.00"), vendedor.getSalario());
	}
}
