package br.com.nucleos.cursomc.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import br.com.nucleos.cursomc.domain.enums.Perfil;
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

@Service
public class DbService {

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

      @Autowired
      private BCryptPasswordEncoder pe;

      public void instantiateTestDatabase() {

            Categoria cat1 = new Categoria(null, "Informática");
            Categoria cat2 = new Categoria(null, "Escritório");
            Categoria cat3 = new Categoria(null, "Casa mesa e banho");
            Categoria cat4 = new Categoria(null, "Eletrônicos");
            Categoria cat5 = new Categoria(null, "Jardinagem");
            Categoria cat6 = new Categoria(null, "Decoração");
            Categoria cat7 = new Categoria(null, "Perfumaria");

            Produto prod1 = new Produto(null, "Computador", new BigDecimal("2000.00"));
            Produto prod2 = new Produto(null, "Impressora", new BigDecimal("800.00"));
            Produto prod3 = new Produto(null, "Mouse", new BigDecimal("80.00"));
            Produto prod4 = new Produto(null, "Mesa de escritório", new BigDecimal("300.00"));
            Produto prod5 = new Produto(null, "Toalha", new BigDecimal("50.00"));
            Produto prod6 = new Produto(null, "Colcha", new BigDecimal("200.00"));
            Produto prod7 = new Produto(null, "TV true color", new BigDecimal("1200.00"));
            Produto prod8 = new Produto(null, "Roçadeira", new BigDecimal("800.00"));
            Produto prod9 = new Produto(null, "Abajur", new BigDecimal("100.00"));
            Produto prod10 = new Produto(null, "Pendente", new BigDecimal("180.00"));
            Produto prod11 = new Produto(null, "Shampoo", new BigDecimal("90.00"));

            cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
            cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));
            cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));
            cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
            cat5.getProdutos().addAll(Arrays.asList(prod8));
            cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
            cat7.getProdutos().addAll(Arrays.asList(prod11));

            prod1.getCategorias().addAll(Arrays.asList(cat1, cat4));
            prod2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
            prod3.getCategorias().addAll(Arrays.asList(cat1, cat4));
            prod4.getCategorias().addAll(Arrays.asList(cat2));
            prod5.getCategorias().addAll(Arrays.asList(cat3));
            prod6.getCategorias().addAll(Arrays.asList(cat3));
            prod7.getCategorias().addAll(Arrays.asList(cat4));
            prod8.getCategorias().addAll(Arrays.asList(cat5));
            prod9.getCategorias().addAll(Arrays.asList(cat6));
            prod10.getCategorias().addAll(Arrays.asList(cat6));
            prod11.getCategorias().addAll(Arrays.asList(cat7));

            this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
            this.produtoRepository
                        .saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10,
                                    prod11));

            Estado est1 = new Estado(null, "Minas Gerais");
            Estado est2 = new Estado(null, "São Paulo");

            Cidade c1 = new Cidade(null, "Uberlândia", est1);
            Cidade c2 = new Cidade(null, "São Paulo", est2);
            Cidade c3 = new Cidade(null, "Campinas", est2);

            est1.getCidades().addAll(Arrays.asList(c1));
            est2.getCidades().addAll(Arrays.asList(c2, c3));

            this.estadoRepository.saveAll(Arrays.asList(est1, est2));
            this.cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

            Cliente cli1 = new Cliente(null, "Maria Silva", "jcarlosrl22@gmail.com", "36378912377",
                        TipoCliente.PESSOAFISICA, pe.encode("123456"));
            cli1.getTelefones().addAll(Arrays.asList("27363326", "93838393"));

            Cliente cli2 = new Cliente(null, "Ana Costa", "jcarlos_rlopes@hotmail.com", "15747867091",
                        TipoCliente.PESSOAFISICA, pe.encode("123456"));
            cli2.getTelefones().addAll(Arrays.asList("38745454", "84563215"));
            cli2.addPerfil(Perfil.ADMIN);

            Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
            Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
            Endereco end3 = new Endereco(null, "Avenida Pontes Vieira", "1050", null, "Centro", "38777012", cli2, c2);

            cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
            cli2.getEnderecos().addAll(Arrays.asList(end3));

            clienteRepository.saveAll(Arrays.asList(cli1, cli2));
            enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

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

            ped1.getItens().addAll(Arrays.asList(ip1, ip2));
            ped2.getItens().addAll(Arrays.asList(ip3));

            prod1.getItens().addAll(Arrays.asList(ip1));
            prod2.getItens().addAll(Arrays.asList(ip3));
            prod3.getItens().addAll(Arrays.asList(ip2));

            itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
      }

}
