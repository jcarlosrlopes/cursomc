package br.com.nucleos.cursomc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.nucleos.cursomc.domain.Categoria;
import br.com.nucleos.cursomc.domain.Cidade;
import br.com.nucleos.cursomc.domain.Cliente;
import br.com.nucleos.cursomc.domain.Endereco;
import br.com.nucleos.cursomc.domain.Estado;
import br.com.nucleos.cursomc.domain.ItemPedido;
import br.com.nucleos.cursomc.domain.Pagamento;
import br.com.nucleos.cursomc.domain.PagamentoBoleto;
import br.com.nucleos.cursomc.domain.PagamentoCartao;
import br.com.nucleos.cursomc.domain.Pedido;
import br.com.nucleos.cursomc.domain.Produto;
import br.com.nucleos.cursomc.domain.enums.EstadoPagamento;
import br.com.nucleos.cursomc.domain.enums.TipoCliente;
import br.com.nucleos.cursomc.repositories.CategoriaRepository;
import br.com.nucleos.cursomc.repositories.CidadeRepository;
import br.com.nucleos.cursomc.repositories.ClienteRepository;
import br.com.nucleos.cursomc.repositories.EnderecoRepository;
import br.com.nucleos.cursomc.repositories.EstadoRepository;
import br.com.nucleos.cursomc.repositories.ItemPedidoRepository;
import br.com.nucleos.cursomc.repositories.PagamentoRepository;
import br.com.nucleos.cursomc.repositories.PedidoRepository;
import br.com.nucleos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Perfumaria");
		Categoria cat4 = new Categoria(null, "Bebidas");
		Categoria cat5 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat6 = new Categoria(null, "Banheiro");
		Categoria cat7 = new Categoria(null, "Cozinha");
		Categoria cat8 = new Categoria(null, "Decoração");
		Categoria cat9 = new Categoria(null, "Pet");
		Categoria cat10 = new Categoria(null, "Jardim");

		Produto prod1 = new Produto(null, "Computador", new BigDecimal("2000.00"));
		Produto prod2 = new Produto(null, "Impressora", new BigDecimal("800.00"));
		Produto prod3 = new Produto(null, "Mouse", new BigDecimal("80.00"));

		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));

		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));

		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10));
		this.produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		this.estadoRepository.saveAll(Arrays.asList(est1, est2));
		this.cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363326", "93838393"));

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Pedido ped1 = new Pedido(null, LocalDateTime.parse("30/09/2017 10:32", dateTimeFormatter), cli1, end1);
		Pedido ped2 = new Pedido(null, LocalDateTime.parse("10/10/2017 19:35", dateTimeFormatter), cli1, end2);

		Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2,
				LocalDate.parse("20/10/2017", dateFormatter), null);
		ped2.setPagamento(pag2);

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

		ItemPedido ip1 = new ItemPedido(ped1, prod1, BigDecimal.ZERO, 1, new BigDecimal("2000.00"));
		ItemPedido ip2 = new ItemPedido(ped1, prod3, BigDecimal.ZERO, 2, new BigDecimal("80.00"));
		ItemPedido ip3 = new ItemPedido(ped2, prod2, BigDecimal.valueOf(100), 1, new BigDecimal("800.00"));

		ped1.getItems().addAll(Arrays.asList(ip1, ip2));
		ped2.getItems().addAll(Arrays.asList(ip3));

		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip3));
		prod3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
