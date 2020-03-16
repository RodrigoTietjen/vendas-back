package br.tietjen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.tietjen.entity.Usuario;
import br.tietjen.entity.Venda;
import br.tietjen.enums.EnumSituacao;
import br.tietjen.repository.VendaRepository;
import br.tietjen.security.UserContext;
import br.tietjen.service.VendaService;

@Service
public class VendaServiceImpl extends CRUDServiceImpl<Venda, VendaRepository> implements VendaService {

	private UserContext userContext;

	@Autowired
	public VendaServiceImpl(VendaRepository repository, UserContext userContext) {
		super(repository);
		this.userContext = userContext;
	}

	@Override
    protected void validatesDependentEntities(Venda entity) {
		Usuario usuario = new Usuario();
		usuario.setId(userContext.getContextSecurity().getIdUsuario());
		entity.setUsuario(usuario );
    	entity.getVendaResumo().setVenda(entity);
    	entity.getProdutos().forEach(p -> p.setVenda(entity));
	}

	@Override
	public Venda finalizeSale(Venda entity) {
		entity.setSituacao(EnumSituacao.FINALIZADO);
		validatesDependentEntities(entity);
		return getRepository().save(entity);
	}
	
	@Override
	public List<Venda> findAll() {
		Sort sort = new Sort(Direction.DESC, "id");
		return getRepository().findAll(sort);
	}
}
