package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import core.MegaChessBot;

@SpringBootApplication
public class MegaChessPlayer{
	
	private MegaChessBot MegaChessBot;
	
	public MegaChessPlayer (){
        this.initializeBot();	
    }	
	
	public static void main(String[] args) {
		SpringApplication.run(MegaChessPlayer.class, args);			
	}	
	
	// public interface
	
	public void initializeBot() {		
		this.MegaChessBot = new MegaChessBot();			
	}

	
}
