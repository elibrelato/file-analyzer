package com.mycompany.analyzer.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.mycompany.analyzer.utilities.Utilities;

public class Config implements Configs {
	
	public static String STRING_SPLIT;

	public static String VENDEDOR_UNIQUE_ID;
    public static List<String> VENDEDOR_NODES;
    
    public static String CLIENTE_UNIQUE_ID;
    public static List<String> CLIENTE_NODES;
       
    public static String VENDA_UNIQUE_ID;
    public static List<String> VENDA_NODES;
    
    private String inputFolder = null;
    private String outputFolder = null;
    private String extension = null;
    private String charset = null;

    public void processa_Config() {
        String dados;
        try {
            FileInputStream f = new FileInputStream("config.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(f, StandardCharsets.UTF_8)); //"UTF-8"));

            dados = reader.readLine();

            if (dados != null) {
                if (dados.startsWith("\uFEFF")) { // it's UTF-8, throw away the BOM character and continue
                    dados = dados.substring(1);
                } else { // it's not UTF-8, reopen
                    reader.close(); // also closes reader
                    f = new FileInputStream("config.txt"); // reopen from the start
                    reader = new BufferedReader(new InputStreamReader(f)); //, "Cp1252"));
                    dados = reader.readLine();
                }
            }

            while (dados != null) {
                processar(dados);
                dados = reader.readLine();
            }
            
            reader.close();
            f.close();
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        } catch (FileNotFoundException ex) {
            System.err.println("Nao foi possivel encontrar o arquivo config.txt.");
            System.exit(0);
        } catch (IOException ex) {
            System.err.println("Nao foi possivel abrir o arquivo config.txt.");
            System.exit(0);
        }
    }

    private String tipo, chave, valor;
    private String[] dados;

    private void processar(String linha) {
        linha = linha.trim();   
        if (linha.startsWith("//") || linha.equals("")) { // verifica comentario ou linha em branco.
            return;
        }
        
        dados = linha.split("=");
        
        if (dados.length == 2) {
            chave = dados[0].toLowerCase().trim();
            valor = dados[1].toLowerCase().trim();
            
            String home = System.getProperty("user.home");
            if (chave.equals("pastadeentrada")) { // configura o parâmetro PastaDeEntrada
                inputFolder = valor.replace("%homepath%", home).replace("%userhome%", home);
                return;
            }
            
            if (chave.equals("pastadesaida")) { // configura o parâmetro PastaDeSaida
                outputFolder = valor.replace("%homepath%", home).replace("%userhome%", home);
                return;
            }
            
            if (chave.equals("extensaodosarquivos")) { // configura o parâmetro ExtensaoDosArquivos
                extension = valor;
                return;
            }
            
            String[] charsets = {"UTF-8", "UTF-16", "ISO-8859-1", "US-ASCII"};        
            if (chave.equals("charset")) { // configura o parâmetro Charset
                for (String charset : charsets) {
                    if (valor.equalsIgnoreCase(charset)) {
                        this.charset = charset;
                    }
                }
                return;
            }
            
            if (chave.equals("separador")) {
            	STRING_SPLIT = valor;
            	return;
            }  
        }
            
        // CONFIGURAÇÕES DOS CAMPOS:      

        // separa os dados e remove espaços dos extremos
        dados = linha.split("[.=]");

        if (dados.length < 3) {
            System.out.println("Erro no arquivo config.txt.");
            System.exit(0);
        }

        tipo = dados[0].toLowerCase().replace(" ", "").trim(); // tipo da entidade (vendedor, cliente, venda etc...)
        chave = dados[1].toLowerCase().replace(" ", "").trim();
        valor = dados[2].toLowerCase().replace(" ", "").trim();

        // valida chaves e valores
        if (!chave.equalsIgnoreCase(CAMPO_ID) && !chave.equalsIgnoreCase(CAMPO_FORMATO)
                && !tipo.equalsIgnoreCase(CAMPO_VENDEDOR)
                && !tipo.equalsIgnoreCase(CAMPO_CLIENTE)
                && !tipo.equalsIgnoreCase(CAMPO_VENDA)) {
            System.out.println("Erro no arquivo config.txt.");
            System.exit(0);
        }

        // configura as entidades
        switch (tipo) {
            case CAMPO_VENDEDOR:
                switch (chave) {
                    case CAMPO_ID:
                    	VENDEDOR_UNIQUE_ID = valor;
                    	break;
                    case CAMPO_FORMATO:     
                        VENDEDOR_NODES = Utilities.getAsList(valor, STRING_SPLIT);
                        break;
                }
                break;
            case CAMPO_CLIENTE:
                switch (chave) {
                    case CAMPO_ID:
                    	CLIENTE_UNIQUE_ID = valor;
                    	break;
                    case CAMPO_FORMATO:
                        CLIENTE_NODES = Utilities.getAsList(valor, STRING_SPLIT);
                        break;
                }
                break;
            case CAMPO_VENDA:
                switch (chave) {
                    case CAMPO_ID:
                    	VENDA_UNIQUE_ID = valor;
                    	break;
                    case CAMPO_FORMATO:
                        VENDA_NODES = Utilities.getAsList(valor, STRING_SPLIT);
                        break;
                }
                break;
        }
    }

    public String getInputFolder() {
        return inputFolder;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public String getExtension() {
        return extension;
    }

    public String getCharset() {
        return charset;
    }
}
