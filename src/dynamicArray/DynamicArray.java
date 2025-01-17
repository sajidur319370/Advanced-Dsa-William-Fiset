package dynamicArray;

/**
 * A generic dynamic array implementation
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {
	private T[] arr;
	private int capacity = 0;
	private int len = 0;

	public DynamicArray() {
		this(16);
	}

	public DynamicArray(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("Illegal capicity" + capacity);
		this.capacity = capacity;
		arr = (T[]) new Object[capacity];
	}

	public int size() {
		return len;
	}

	public boolean isEmpty() {

		return size() == 0;
	}

	public T get(int index) {

		return arr[index];
	}

	public void set(int index, T elem) {
		arr[index] = elem;
	}

	public void clear() {
		for (int i = 0; i < capacity; i++) {
			arr[i] = null;
		}
		len = 0;
	}

	public void add(T elem) {
		if (len + 1 >= capacity) {
			if (capacity == 0) {
				capacity = 1;
			} else {
				capacity *= 2;
			}
			T[] newArr = (T[]) new Object[capacity];
			for (int i = 0; i < len; i++) {
				newArr[i] = arr[i];
			}
			arr = newArr;
		}
		arr[len++] = elem;
	}

	public T removeAt(int rm_index) {
		if (rm_index < 0 && rm_index >= len) {
			throw new IndexOutOfBoundsException();
		}
		T data = arr[rm_index];
		T[] newArr = (T[]) new Object[len - 1];
		for (int i = 0, j = 0; i < len; i++, j++) {
			if (i == rm_index) {
				j--;
			} else {
				newArr[j] = arr[i];
			}
		}
		arr = newArr;
		capacity = --len;
		return data;
	}

	public boolean removeObject(Object obj) {
		for (int i = 0; i < len; i++) {
			if (arr[i].equals(obj)) {
				removeAt(i);
				return true;
			}
		}
		return false;

	}

	public int indexOf(Object obj) {
		for (int i = 0; i < len; i++) {
			if (arr[i].equals(obj)) {
				return i;
			}
		}
		return -1;
	}

	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index = 0;

			public boolean hasNext() {
				return index < len;
			}

			public T next() {
				return arr[index++];
			}
		};
	}

	@Override
	public String toString() {
		if (len == 0) {
			return "[]";
		} else {
			StringBuilder sb = new StringBuilder(len).append("[");
			for (int i = 0; i < len - 1; i++) {
				sb.append(arr[i] + ",");
			}
			return sb.append(arr[len - 1] + "]").toString();
		}

	}
}
