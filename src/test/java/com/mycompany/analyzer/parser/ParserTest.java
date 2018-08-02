package com.mycompany.analyzer.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mycompany.analyzer.config.Config;
import com.mycompany.analyzer.dados.Dados;

public class ParserTest {

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
	public void testParse() {
		File file = getTemporaryFile();
        new Parser().parse(file);
        FileUtils.deleteQuietly(file);
        
        assertEquals("Diego", Dados.getVendedores().get(0).getNome());
        assertEquals("1234567891234", Dados.getVendedores().get(0).getCpf());        
        assertEquals(new BigDecimal("5000.00"), Dados.getVendedores().get(0).getSalario());
        assertEquals("Renato", Dados.getVendedores().get(1).getNome());
        assertEquals("3245678865434", Dados.getVendedores().get(1).getCpf()); 
        assertEquals(new BigDecimal("4000.00"), Dados.getVendedores().get(1).getSalario());
        
        assertEquals("Jose da Silva", Dados.getClientes().get(0).getNome());
        assertEquals("2345675434544345", Dados.getClientes().get(0).getCnpj());
        assertEquals("Rural", Dados.getClientes().get(0).getRamo());
        assertEquals("Eduardo Gonsalvez Pereira", Dados.getClientes().get(1).getNome());
        assertEquals("2345675433444345", Dados.getClientes().get(1).getCnpj());
        assertEquals("Rural", Dados.getClientes().get(1).getRamo());
        
        assertEquals("10", Dados.getVendas().get(0).getIdVenda());
        assertEquals("11010", Dados.getVendas().get(0).getIdItem());
        assertEquals(300, Dados.getVendas().get(0).getItemQuantidade());
        assertEquals(new BigDecimal("3403.30"), Dados.getVendas().get(0).getItemPreco());
        assertEquals("Diego", Dados.getVendas().get(0).getVendedor());
        assertEquals(new BigDecimal("3403.30").multiply(new BigDecimal("300")), Dados.getVendas().get(0).getValorDaVenda());        
        assertEquals("08", Dados.getVendas().get(1).getIdVenda());
        assertEquals("13410", Dados.getVendas().get(1).getIdItem());
        assertEquals(540, Dados.getVendas().get(1).getItemQuantidade());
        assertEquals(new BigDecimal("2400.10"), Dados.getVendas().get(1).getItemPreco());
        assertEquals("Renato", Dados.getVendas().get(1).getVendedor());
        assertEquals(new BigDecimal("2400.10").multiply(new BigDecimal("540")), Dados.getVendas().get(1).getValorDaVenda());
	}
	
	private File getTemporaryFile() {       
        Collection<String> dados = new ArrayList<>();
        File file = new File(FileUtils.getTempDirectoryPath() + File.separatorChar + "teste1.tmp");
        dados.add("001;1234567891234;Diego;5000.00");
        dados.add("002;2345675434544345;Jose da Silva;Rural");
        dados.add("002;2345675433444345;Eduardo Gonsalvez Pereira;Rural");
        dados.add("001;3245678865434;Renato;4000.00");
        dados.add("003;10;11010;300;3403.30;Diego");
        dados.add("003;08;13410;540;2400.10;Renato");           
        try {
            FileUtils.writeLines(file, null, dados, null);
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return file;
    }
}
