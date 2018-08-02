package com.mycompany.analyzer.relatorio;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.mycompany.analyzer.dados.Dados;

public class Relatorio {
	protected File file;
	protected final Collection<String> dados = new ArrayList<>(); 
	
	/**
     * Inicia um relatorio
     * @param file - Arquivo previamente processado que iremos gerar o relatorio.
     */
	public Relatorio(File file) {
        this.file = file;
    }
	
	/**
     * Gera o relatorio e salva os dados com o charset utilizado pela plataforma.
     * @param outputFolder Pasta onde serao salvos os arquivos do relatorio.
     * @param extension Extensao do arquivo de saida.
     */
	public void gerarRelatorio(File outputFolder, String extension) {
        gerarRelatorio(outputFolder, extension, null);
    }
	
	/**
     * Gera o relatorio e salva os dados com o charset especificado. 
     * 
     * @param outputFolder Pasta onde serao salvos os arquivos do relatorio
     * @param extension Extensao do arquivo de saida.
     * @param charset Charset utilizado para gravar o arquivo de saida.
     * Charsets suportados: US-ASCII, UTF-8, UTF-16, ISO-8859-1 (ANSI).
     * Caso charset=null, sera utilizado o charset padrao da plataforma (no Windows sera ANSI).
     */
    public void gerarRelatorio(File outputFolder, String extension, String charset) {
        dados.clear();
        System.out.println("Gerando relatorio do arquivo: " + file.getName()); 
        dados.add("Quantidade de clientes: " + getQuantidadeDeClientes());
        dados.add("Quantidade de vendedores: " + getQuantidadeDeVendedores());
        dados.add("ID da venda de valor mais alto: " + getIdDaVendaMaisCara());
        dados.add("Vendedor que menos vendeu: " + getPiorVendedor());              
        File outputFile = getOutputFile(outputFolder, file, extension);
        salvar(dados, outputFile, charset); 
    }
    
    protected boolean primeiro;
    protected BigDecimal maior, menor, valor;    
    protected String resultado;
    
    /**
     * Retorna a quantidade de vendedores do arquivo que foi processado.
     * @return Um inteiro contendo a quantidade de vendedores.
     */
    protected int getQuantidadeDeVendedores() {
        return Dados.getVendedores().size();
    }
    
    /**
     * Retorna a quantidade de clientes do arquivo que foi processado.
     * @return Um inteiro contendo a quantidade de clientes.
     */
    protected int getQuantidadeDeClientes() {
        return Dados.getClientes().size();
    }
    
    /**
     * Retorna o ID da venda de maior valor do arquivo que foi processado.
     * @return Uma String contendo o ID da venda de maior valor.
     */
    protected String getIdDaVendaMaisCara() {
    	maior = new BigDecimal("0");
        resultado = "err";
        Dados.getVendas().forEach(venda -> {
        	valor = venda.getValorDaVenda();
        	if (valor.compareTo(maior) > 0) {
                maior = valor;
                resultado = venda.getIdVenda();
            }
        });
        return resultado;
    }
    
    /**
     * Retorna o pior vendedor do arquivo que foi processado.
     * @return Uma String contendo o nome do pior vendedor.
     */
    protected String getPiorVendedor() {
        primeiro = true;
        resultado = "err";
        menor = new BigDecimal("0");
        Dados.getVendas().forEach(venda -> {
            valor = venda.getValorDaVenda();
            if (primeiro == true || valor.compareTo(menor) < 0) {
                menor = valor;
                resultado = venda.getVendedor();
                primeiro = false;
            }
        });
        return resultado;
    }
    
    protected File getOutputFile(File outputFolder, File file, String extension) {
        return new File (outputFolder.getAbsolutePath() + File.separatorChar
                + file.getName() + FilenameUtils.EXTENSION_SEPARATOR_STR + extension);
    }

    /**
     * Salva o relatorio.
     * @param dados Collection contendo as linhas de dados a ser salvo.
     * @param file Arquivo de saida. A pasta de saida deve ser vonfigurada no arquivo config.txt.
     * @param charset Charset utilizado para gravar o arquivo de saida.
     * Charsets suportados: US-ASCII, UTF-8, UTF-16, ISO-8859-1. 
     * Caso charset=null, sera utilizado o charset padrao da plataforma (ANSI no case do windows).
     */
    protected void salvar(Collection<String> dados, File file, String charset) {
        try {
            FileUtils.writeLines(file, charset, dados, null);
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
