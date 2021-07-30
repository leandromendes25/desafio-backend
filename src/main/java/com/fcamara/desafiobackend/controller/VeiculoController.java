package com.fcamara.desafiobackend.controller;

import com.fcamara.desafiobackend.config.security.TokenService;
import com.fcamara.desafiobackend.controller.dto.VeiculoDto;
import com.fcamara.desafiobackend.controller.form.VeiculoForm;
import com.fcamara.desafiobackend.model.Estabelecimento;
import com.fcamara.desafiobackend.model.Usuario;
import com.fcamara.desafiobackend.model.Veiculo;
import com.fcamara.desafiobackend.repository.EstabelecimentoRepository;
import com.fcamara.desafiobackend.repository.UsuarioRepository;
import com.fcamara.desafiobackend.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fcamara.desafiobackend.repository.VeiculoRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<VeiculoDto>> getAll() {
        return  ResponseEntity.ok(VeiculoDto.converter(repository.findAll()));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestHeader("Authorization") String token, @RequestBody @Valid VeiculoForm form) {
        Long usuarioId = tokenService.getUsuarioId(token.substring(7));
        Optional<Usuario> usuarioDb = usuarioRepository.findById(usuarioId);
        Estabelecimento estabelecimento = usuarioDb.get().getEstabelecimento();
        //Validar veiculo
        Optional<Veiculo> veiculoDb = repository.findByPlacaAndEstabelecimento(form.getPlaca(), estabelecimento);
        if(veiculoDb.isPresent()) {
            return ResponseEntity.badRequest().body(JsonResponse.message("Veiculo ja cadastrado nesse estacionamento"));
        }
        Veiculo veiculo = form.converter();
        estabelecimento.adicionarVeiculo(veiculo);
        estabelecimentoRepository.save(estabelecimento);
        repository.save(veiculo);
        return ResponseEntity.ok(VeiculoDto.converter(veiculo));
    }

}
