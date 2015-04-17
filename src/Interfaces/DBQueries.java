package Interfaces;

import java.util.List;

public interface DBQueries<T> {
    public List<T> buscarTodos();
    public T buscarPorID(String code);
    public boolean insert(T entity);
}
