package aufgabe1;

import java.util.Arrays;

/**
 *
 * @author oliverbittel
 * @since 04.03.2021
 */
public class ArrayFrequencyTable extends AbstractFrequencyTable {
	private int size;
	private Word fqTable[];
	private final int DEFAULT_SIZE = 100;

	public ArrayFrequencyTable() {
		clear();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public final void clear() {
		fqTable = new Word[DEFAULT_SIZE];
		this.size = 0;
	}

	@Override
	public void add(String w, int f) {
		if(size() == fqTable.length) {
			fqTable = Arrays.copyOf(fqTable, fqTable.length + DEFAULT_SIZE);
		}
		for (int i = 0; i <= this.size; i++) {
			if (fqTable[i] != null && w.equals(fqTable[i].getWord())) {
				fqTable[i].addFrequency(f);
				moveLeft(i);
				return;
			} else if (fqTable[i] == null) {
				fqTable[i] = new Word(w, f);
				this.size++;
				moveLeft(i);
				return;
			}
		}
	}


	@Override
	public void add(String w) {
		add(w, 1);
	}


	@Override
	public Word get(int pos) {
		return fqTable[pos];
	}

	@Override
	public int get(String w) {
		for (int i = 0; i < this.size(); i++) {
			if (w.equals(fqTable[i].getWord())) {
				return fqTable[i].getFrequency();
			}
		}
		return 0;
	}

	private void moveLeft(int pos) {
		Word w = fqTable[pos];
		while (pos > 0 && w.getFrequency() > fqTable[pos - 1].getFrequency()) {
			fqTable[pos] = fqTable[pos - 1];
			pos--;
		}
		fqTable[pos] = w;

	}
}
