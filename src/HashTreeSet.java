import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class HashTreeSet implements Set<Tile> {
	
	TreeSet<Tile> treeSet;
	HashSet<Tile> hashSet;
	
	public HashTreeSet(){
		
		treeSet = new TreeSet<Tile>(new Comparator<Tile>() {

			@Override
			public int compare(Tile o1, Tile o2) {
				int score = o1.getFScore() - o2.getFScore();
				if (score == 0){
					return -1;
				}
				return score;
			}
			
		});
        
        hashSet = new HashSet<Tile>();
        
	}
	
	public Tile pollFirst(){
		return treeSet.pollFirst();
	}

	@Override
	public boolean add(Tile e) {
		treeSet.add(e);
		hashSet.add(e);
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Tile> c) {
		treeSet.addAll(c);
		hashSet.addAll(c);
		return false;
	}

	@Override
	public void clear() {
		treeSet.clear();
		hashSet.clear();
	}

	@Override
	public boolean contains(Object o) {
		return hashSet.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return hashSet.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return hashSet.isEmpty();
	}

	@Override
	public Iterator<Tile> iterator() {
		return hashSet.iterator();
	}

	@Override
	public boolean remove(Object o) {
		if (hashSet.remove(o) && treeSet.remove(o)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (hashSet.removeAll(c) && treeSet.removeAll(c)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (hashSet.retainAll(c) && treeSet.retainAll(c)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		return hashSet.size();
	}

	@Override
	public Object[] toArray() {
		return hashSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return hashSet.toArray(a);
	}

}
