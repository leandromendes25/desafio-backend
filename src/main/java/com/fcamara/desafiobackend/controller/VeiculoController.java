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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Controle de veiculos")
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
    @Operation(summary = "Utilizado para listar veiculos")
    @ApiResponse(responseCode = "200", description = "VEICULOS LISTADOS COM SUCESSO")
    @ApiResponse(responseCode = "400", description = "FALHA AO LISTAR VEICULOS")
    @GetMapping
    public ResponseEntity<List<VeiculoDto>> getAll() {
        return ResponseEntity.ok(VeiculoDto.converter(repository.findAll()));
    }
    @Operation(summary = "Utilizado para registrar novos veiculos")
    @ApiResponse(responseCode = "200", description = "VEICULO REGISTRADO COM SUCESSO")
    @ApiResponse(responseCode = "400", description = "VEICULO JÁ REGISTRADO")
    @PostMapping
    public ResponseEntity<?> insert(@RequestHeader("Authorization") String token, @RequestBody @Valid VeiculoForm form) {
        Long usuarioId = tokenService.getUsuarioId(token.substring(7));
        Optional<Usuario> usuarioDb = usuarioRepository.findById(usuarioId);
        Estabelecimento estabelecimento = usuarioDb.get().getEstabelecimento();
        //Validar veiculo
        Optional<Veiculo> veiculoDb = repository.findByPlacaAndEstabelecimento(form.getPlaca(), estabelecimento);
        if (veiculoDb.isPresent()) {
            return ResponseEntity.badRequest().body(JsonResponse.message("Veiculo ja cadastrado nesse estacionamento"));
        }
        Veiculo veiculo = form.converter();
        estabelecimento.adicionarVeiculo(veiculo);
        estabelecimentoRepository.save(estabelecimento);
        repository.save(veiculo);
        return ResponseEntity.status(201).body((VeiculoDto.converter(veiculo)));
    }
    @Operation(summary = "Utilizado para atualizar registro do veiculo")
    @ApiResponse(responseCode = "200", description = "REGISTRO ATUALIZADO COM SUCESSO")
    @ApiResponse(responseCode = "400", description = "FALHA AO ATUALIZAR REGISTRO")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestHeader("Authorization") String token, @RequestBody @Valid VeiculoForm form){
        Long usuarioId = tokenService.getUsuarioId(token.substring(7));
        Optional<Usuario> usuarioDb = usuarioRepository.findById(usuarioId);
        Estabelecimento estabelecimento = usuarioDb.get().getEstabelecimento();
        Optional<Veiculo> veiculoDb = repository.findById(id);
        boolean contains = estabelecimento.getVeiculos().contains(veiculoDb.get());
        if(contains) {
            Veiculo veiculo = form.converter();
            veiculo.setId(id);
            estabelecimento.adicionarVeiculo(veiculo);
            estabelecimentoRepository.save(estabelecimento);
            repository.save(veiculo);
            return ResponseEntity.ok(VeiculoDto.converter(veiculo));
        }
        return ResponseEntity.status(403).body(JsonResponse.message("Você não possui autorização para isso"));
    }
    @Operation(summary = "Deleta registro do estabelecimento")
    @ApiResponse(responseCode = "200", description = "REGISTRO DELETADO COM SUCESSO")
    @ApiResponse(responseCode = "400", description = "FALHA AO DELETAR REGISTRO")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        Long usuarioId = tokenService.getUsuarioId(token.substring(7));
        Optional<Usuario> usuarioDb = usuarioRepository.findById(usuarioId);
        Estabelecimento estabelecimento = usuarioDb.get().getEstabelecimento();
        Optional<Veiculo> veiculoDb = repository.findById(id);
        boolean contains = estabelecimento.getVeiculos().contains(veiculoDb.get());
        if(contains) {
            repository.delete(veiculoDb.get());
            return ResponseEntity.ok(JsonResponse.message("Veiculo excluido com sucesso"));
        }
        return ResponseEntity.status(403).body(JsonResponse.message("Você não possui autorização para isso"));

    }
}
