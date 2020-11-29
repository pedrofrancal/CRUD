package entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class CarrinhoCompra {
	private int id;
	private Cliente cliente;
	private ArrayList<Produto> pedidos;
	private double total;
	private LocalDate dataPedido;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ArrayList<Produto> getPedidos() {
		return pedidos;
	}
	public void setPedidos(ArrayList<Produto> pedidos) {
		this.pedidos = pedidos;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public LocalDate getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}
}
