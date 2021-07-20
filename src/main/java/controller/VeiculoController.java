package controller;

import model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import repository.VeiculoRepository;

import java.util.List;

@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoRepository repository;

    @GetMapping
    public List<Veiculo> lista(String placaVeiculo){
    if(placaVeiculo == null){
        throw new IllegalArgumentException("Placa não cadastrada");
    }
    List<Veiculo> veiculos = repository.findByPlacaVeiculo(placaVeiculo);
    return null;
    }
}
