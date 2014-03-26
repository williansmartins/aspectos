package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.vo.ProdutoVO;

public interface IProdutoDAO
{
    void salvar( ProdutoVO produto );
    void remover( ProdutoVO produto );
    List<ProdutoVO> listarTodos( );
    List<ProdutoVO> listarPorNome( String nome );
    ProdutoVO buscarPorId( Long id );
}