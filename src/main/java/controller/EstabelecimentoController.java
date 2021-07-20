package controller;

import model.Estabelecimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.EstabelecimentoRepository;

import java.util.List;

@RequestMapping("/")
@RestController
public class EstabelecimentoController {
    @Autowired
    private EstabelecimentoRepository repository;

    @GetMapping("/estabelecimentos")
    public List<Estabelecimento> lista(String nomeEstabelecimento){
    if(nomeEstabelecimento == null){
        List<Estabelecimento> lista = repository.findAll();
    }
    List<Estabelecimento> estabelecimentos = repository.findByNomeEstabelecimento(nomeEstabelecimento);
    return null;
    }
}
