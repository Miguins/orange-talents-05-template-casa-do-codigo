package br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.response;

public class ClienteResponse {
	
	private Long id;
	
	public ClienteResponse(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
