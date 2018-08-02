package com.mycompany.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.mycompany.analyzer.config.Config;
import com.mycompany.analyzer.dados.Dados;
import com.mycompany.analyzer.parser.Parser;
import com.mycompany.analyzer.relatorio.Relatorio;

public class App extends Thread 
{
	private Config config = new Config();
    private Parser parser = new Parser();
    private Relatorio relatorio;
    
    private Collection<File> arquivos;
    
    // Configs
    private static File inputFolder;
    private static File outputFolder;
    private static String[] extIn = new String[1];
    private static String extOut, charset;
        
    public void run() {
    	
    	System.out.println();
        System.out.println("Iniciando....");
        System.out.print("Carregando configuracoes...");
        
        config.processa_Config(); // processa o arquivo config.txt
        setConfigs(); // carrega as configurações 
        
        System.out.println(" Concluido!");
        System.out.println("Pasta de trabalho: " + inputFolder.getPath());
        System.out.println("Pasta de saida   : " + outputFolder.getPath());
        System.out.println("Extensao dos arquivos a serem processados: ." + extIn[0]);
        System.out.println("Charset do arquivo de saida: " + (charset == null ? "Default" : charset));
        System.out.println("Executando...");
        
        Map<String, File> arquivosIn = new HashMap<>();
        Collection<String> arquivosOut = new ArrayList<>();
        
        
        while (true) {
            
            if (!inputFolder.exists()) {
                try {
					FileUtils.forceMkdir(inputFolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }

            if (!outputFolder.exists()) {
                try {
					FileUtils.forceMkdir(outputFolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            
            arquivosIn.clear();
            arquivosOut.clear();
            arquivos = FileUtils.listFiles(inputFolder, extIn, false);
            arquivos.forEach(arquivo -> {
                arquivosIn.put(arquivo.getName(), arquivo);
            });
            
            arquivos = FileUtils.listFiles(outputFolder, null, false);
            arquivos.forEach(arquivo -> {
            	arquivosOut.add(FilenameUtils.getBaseName(arquivo.getName()));
            });
            
            arquivos.clear();
            arquivosIn.forEach((nome, file) -> {
               if (!arquivosOut.contains(nome))
                   arquivos.add(file);
            });     

            if (arquivos.size() > 0) {
            	arquivos.forEach(file -> {
	            	parser.parse(file);
	        		relatorio = new Relatorio(file);
	        		relatorio.gerarRelatorio(outputFolder, extOut, charset);
	        		Dados.clear();
            	});
            }     
            
            try {Thread.sleep(5000);} catch (InterruptedException ex) {}
        }    	
    }
    
    /**
     * Define as configurações.
     * Utiliza os valores que foram carregados do arquivo config.txt. 
     * Caso algum valor nao esteja configurado, sera definido um valor default.
     */
    private void setConfigs() {
        inputFolder = new File((config.getInputFolder() == null
                ? System.getProperty("user.home") + File.pathSeparator + "dados" + File.pathSeparator + "in" // dafault 
                : config.getInputFolder()));
 
        outputFolder = new File((config.getOutputFolder() == null
                ? System.getProperty("user.home") + File.pathSeparator + "dados" + File.pathSeparator + "out"  // dafault
                : config.getOutputFolder()));

        extIn[0] = (config.getExtension() == null
                ? "dat"
                : config.getExtension().replace(".", ""));
        
        extOut = "proc";

        charset = config.getCharset();
    }
    
    public static void main( String[] args )
    {
        new App().start();
    }
}
