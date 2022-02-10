package br.com.nucleos.cursomc.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.nucleos.cursomc.domain.PagamentoBoleto;

@Service
public class BoletoService {

   public void preencherPagagamentoBoleto(PagamentoBoleto pgto, LocalDateTime instanteDoPedido) {
      pgto.setDataVencimento(pgto.getDataPagamento().plusDays(7));
   }

}
