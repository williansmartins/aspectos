package br.com.fiap.dao;

import java.math.BigInteger;
import java.util.List;

import br.com.fiap.model.Compra;

public interface ICompraDAO
{
    void salvar( Compra objeto );
    void remover( Compra objeto );
    List<Compra> listarTudo( );
    List<Compra> buscarPorCliente( BigInteger id );
}