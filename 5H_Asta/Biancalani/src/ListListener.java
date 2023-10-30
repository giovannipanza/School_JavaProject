import java.util.List;

public interface ListListener<E> {
    void onAdd(List<E> list, E element);
    void onDelete(List<E> list, E element);
}