package br.tietjen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tietjen.dto.VendaDTO;
import br.tietjen.entity.Venda;
import br.tietjen.mapper.VendaMapper;
import br.tietjen.service.VendaService;

@CrossOrigin
@RestController
@RequestMapping("api/venda")
public class VendaController extends CRUDController<VendaDTO, Venda, VendaService, VendaMapper> {

	@Autowired
	public VendaController(VendaService service, VendaMapper mapper) {
		super(service, mapper);
	}

	@PostMapping(value = "/finalizar-venda", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> finalizaVenda(@RequestBody VendaDTO dto) {
		return new ResponseEntity<>(getMapper().toDto(getService().finalizeSale(getMapper().toEntity(dto))), HttpStatus.OK);
	}
	


}
