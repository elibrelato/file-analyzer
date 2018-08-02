package com.mycompany.analyzer.factories;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mycompany.analyzer.config.Config;
import com.mycompany.analyzer.entities.Cliente;

public class ClienteFactoryTest {

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
	public void testGetCliente() {
		String dados = "002;2345675434544345;Jose da Silva;Rural";
        Cliente cliente = new ClienteFactory().getCliente(dados);
        assertEquals("Jose da Silva", cliente.getNome());
        assertEquals("2345675434544345", cliente.getCnpj());
        assertEquals("Rural", cliente.getRamo());
	}
}
