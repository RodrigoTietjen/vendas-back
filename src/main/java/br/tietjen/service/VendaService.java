package br.tietjen.service;

import br.tietjen.entity.Venda;

public interface VendaService extends CRUDService<Venda> {

	Venda finalizeSale(Venda entity);

}
