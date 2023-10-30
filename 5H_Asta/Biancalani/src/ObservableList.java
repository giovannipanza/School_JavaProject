import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

// implements List<E>
public class ObservableList<E> implements Iterable<E> {
    private List<E> decoratedList;
    private ListListener<E> listener;

    public ObservableList(List<E> targetList) {
        decoratedList = targetList;
    }

    public void setListener(final ListListener<E> listener) {
        this.listener = listener;
    }

    public boolean add(E element) {
        final boolean result = decoratedList.add(element);
        doOnAdd(element);
        return result;
    }

    public E remove(int index) {
        final E result = decoratedList.remove(index);
        doOnRemove(result);
        return result;
    }

    private void doOnAdd(final E element) {
        if (listener != null) {
            listener.onAdd(decoratedList, element);
        }
    }

    private void doOnRemove(final E element) {
        if (listener != null) {
            listener.onDelete(decoratedList, element);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return decoratedList.iterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        decoratedList.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return decoratedList.spliterator();
    }
}