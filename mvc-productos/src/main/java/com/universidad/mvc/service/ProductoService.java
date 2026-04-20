package com.universidad.mvc.service;

import com.universidad.mvc.model.Producto;
import java.util.*;

public class ProductoService {

    private static final List<Producto> lista = new ArrayList<>();
    private static int contador = 1;

    static {
        lista.add(new Producto(contador++, "Laptop", "Computadores", 2500000, 5));
        lista.add(new Producto(contador++, "Mouse", "Periféricos", 80000, 20));
    }

    public List<Producto> listar() {
        return lista;
    }

    public Producto buscar(int id) {
        return lista.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void guardar(Producto p) {
        validar(p);
        p.setId(contador++);
        lista.add(p);
    }

    public void actualizar(Producto p) {
        validar(p);
        Producto existente = buscar(p.getId());
        if (existente != null) {
            existente.setNombre(p.getNombre());
            existente.setCategoria(p.getCategoria());
            existente.setPrecio(p.getPrecio());
            existente.setStock(p.getStock());
        }
    }

    public void eliminar(int id) {
        lista.removeIf(p -> p.getId() == id);
    }

    private void validar(Producto p) {
        if (p.getNombre() == null || p.getNombre().isEmpty())
            throw new IllegalArgumentException("Nombre obligatorio");

        if (p.getPrecio() <= 0)
            throw new IllegalArgumentException("Precio inválido");

        if (p.getStock() < 0)
            throw new IllegalArgumentException("Stock inválido");
    }
}