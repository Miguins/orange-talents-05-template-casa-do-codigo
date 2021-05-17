package br.com.zupacademy.lucasmiguins.casadocodigo.exception.handler;

public class ErroFormDTO {
	public String campo;
	public String erro;
	
	public ErroFormDTO(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
}
