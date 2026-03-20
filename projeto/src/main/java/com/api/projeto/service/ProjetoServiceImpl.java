package com.api.projeto.service;

import com.api.projeto.dto.request.ProjetoRequest;
import com.api.projeto.dto.request.ProjetoUsuarioRequest;
import com.api.projeto.dto.response.ProjetoResponse;
import com.api.projeto.dto.response.ProjetoUsuarioResponse;
import com.api.projeto.entity.Projeto;
import com.api.projeto.entity.ProjetoUsuario;
import com.api.projeto.entity.ProjetoUsuarioId;
import com.api.projeto.entity.TipoProjeto;
import com.api.projeto.exception.RecursoNaoEncontradoException;
import com.api.projeto.repository.ProjetoRepository;
import com.api.projeto.repository.ProjetoUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final ProjetoUsuarioRepository projetoUsuarioRepository;

    @Override
    public List<ProjetoResponse> listarTodos() {
        return projetoRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public ProjetoResponse buscarPorId(Integer id) {
        return toResponse(buscarOuLancar(id));
    }

    @Override
    public ProjetoResponse criar(ProjetoRequest request) {
        validarDatas(request.dataInicio(), request.dataFim());
        validarTipoProjeto(request);
        if (projetoRepository.existsByCodigo(request.codigo())) {
            throw new IllegalArgumentException("Código já existe: " + request.codigo());
        }
        Projeto projeto = Projeto.builder()
                .nome(request.nome())
                .codigo(request.codigo())
                .tipoProjeto(request.tipoProjeto())
                .valorHoraBase(request.valorHoraBase())
                .horasContratadas(request.horasContratadas())
                .dataInicio(request.dataInicio())
                .dataFim(request.dataFim())
                .responsavelId(request.responsavelId())
                .build();
        return toResponse(projetoRepository.save(projeto));
    }

    @Override
    public ProjetoResponse atualizar(Integer id, ProjetoRequest request) {
        Projeto projeto = buscarOuLancar(id);
        validarDatas(request.dataInicio(), request.dataFim());
        validarTipoProjeto(request);
        if (projetoRepository.existsByCodigoAndIdNot(request.codigo(), id)) {
            throw new IllegalArgumentException("Código já existe: " + request.codigo());
        }
        projeto.setNome(request.nome());
        projeto.setCodigo(request.codigo());
        projeto.setTipoProjeto(request.tipoProjeto());
        projeto.setValorHoraBase(request.valorHoraBase());
        projeto.setHorasContratadas(request.horasContratadas());
        projeto.setDataInicio(request.dataInicio());
        projeto.setDataFim(request.dataFim());
        projeto.setResponsavelId(request.responsavelId());
        return toResponse(projetoRepository.save(projeto));
    }

    @Override
    public void deletar(Integer id) {
        buscarOuLancar(id);
        projetoRepository.deleteById(id);
    }

    @Override
    public ProjetoUsuarioResponse adicionarUsuario(Integer projetoId, ProjetoUsuarioRequest request) {
        buscarOuLancar(projetoId);
        ProjetoUsuarioId pkId = new ProjetoUsuarioId(projetoId, request.usuarioId());
        if (projetoUsuarioRepository.existsById(pkId)) {
            throw new IllegalArgumentException("Usuário já está vinculado a este projeto.");
        }
        ProjetoUsuario pu = ProjetoUsuario.builder()
                .id(pkId)
                .valorHora(request.valorHora())
                .build();
        return toUsuarioResponse(projetoUsuarioRepository.save(pu));
    }

    @Override
    public void removerUsuario(Integer projetoId, Integer usuarioId) {
        ProjetoUsuarioId pkId = new ProjetoUsuarioId(projetoId, usuarioId);
        if (!projetoUsuarioRepository.existsById(pkId)) {
            throw new RecursoNaoEncontradoException(
                "Vínculo não encontrado: projetoId=" + projetoId + ", usuarioId=" + usuarioId);
        }
        projetoUsuarioRepository.deleteById(pkId);
    }

    @Override
    public List<ProjetoUsuarioResponse> listarUsuarios(Integer projetoId) {
        buscarOuLancar(projetoId);
        return projetoUsuarioRepository.findByIdProjetoId(projetoId)
                .stream().map(this::toUsuarioResponse).toList();
    }

    // Lança 404 se o projeto não existir
    private Projeto buscarOuLancar(Integer id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto não encontrado: id=" + id));
    }

    private void validarDatas(LocalDate dataInicio, LocalDate dataFim) {
        if (dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException("data_fim não pode ser anterior a data_inicio.");
        }
    }

    // Regras de negócio por tipo de projeto
    private void validarTipoProjeto(ProjetoRequest request) {
        if (request.tipoProjeto() == TipoProjeto.HORA_FECHADA && request.horasContratadas() == null) {
            throw new IllegalArgumentException(
                "horas_contratadas é obrigatório para projetos do tipo hora_fechada.");
        }
        if (request.tipoProjeto() == TipoProjeto.ALOCACAO && request.horasContratadas() != null) {
            throw new IllegalArgumentException(
                "horas_contratadas não deve ser informado para projetos do tipo alocacao.");
        }
    }

    private ProjetoResponse toResponse(Projeto p) {
    BigDecimal valorTotal = null;
    if (p.getTipoProjeto() == TipoProjeto.HORA_FECHADA && p.getHorasContratadas() != null) {
        valorTotal = p.getValorHoraBase()
                .multiply(p.getHorasContratadas())
                .setScale(2, java.math.RoundingMode.HALF_UP);
        }
    return new ProjetoResponse(
        p.getId(), p.getNome(), p.getCodigo(), p.getTipoProjeto(),
        p.getValorHoraBase(), p.getHorasContratadas(), valorTotal,
        p.getDataInicio(), p.getDataFim(), p.getResponsavelId()
        );
    }
    private ProjetoUsuarioResponse toUsuarioResponse(ProjetoUsuario pu) {
        return new ProjetoUsuarioResponse(
            pu.getId().getProjetoId(), pu.getId().getUsuarioId(), pu.getValorHora()
        );
    }
}
