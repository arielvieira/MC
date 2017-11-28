package com.arielvieira.mc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arielvieira.mc.domain.ItemPedido;
import com.arielvieira.mc.domain.PagamentoComBoleto;
import com.arielvieira.mc.domain.Pedido;
import com.arielvieira.mc.domain.enums.EstadoPagamento;
import com.arielvieira.mc.repositories.ClienteRepository;
import com.arielvieira.mc.repositories.ItemPedidoRepository;
import com.arielvieira.mc.repositories.PagamentoRepository;
import com.arielvieira.mc.repositories.PedidoRepository;
import com.arielvieira.mc.repositories.ProdutoRepository;
import com.arielvieira.mc.services.exceptions.ObjetNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private PedidoRepository repo;
	@Autowired
	private ClienteRepository clienteRepository;
	

	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjetNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName());
		} else {
			return obj;
		}
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PEDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		System.out.println(obj);
		return obj;
	}
}
