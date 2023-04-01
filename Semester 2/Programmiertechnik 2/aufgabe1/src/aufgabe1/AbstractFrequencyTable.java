package aufgabe1;

/**
 *
 * @author oliverbittel
 * @since 22.2.2019
 */
public abstract class AbstractFrequencyTable implements FrequencyTable {
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public void add(String w) {
		add(w, 1);
	}

	@Override
	public void addAll(FrequencyTable fq) {
		for (int i = 0; i < fq.size(); i++) {
			String w = fq.get(i).getWord();
			int j = fq.get(i).getFrequency();
			this.add(w, j);
		}
	}


	@Override
	public void collectMostFrequent(FrequencyTable fq) {
		fq.clear();
		if (this.get(0) != null) {
			int f = this.get(0).getFrequency();
			fq.add(this.get(0).getWord(), f);
		} else {
			return;
		}
		int wtf = this.get(0).getFrequency();
		for (int i = 1; i < this.size(); i++) {
			if (this.get(i) != null && this.get(i).getFrequency() == wtf) {
				fq.add(this.get(i).getWord(), wtf);
			} else {
				return;
			}
		}
	}

	@Override
	public void collectLeastFrequent(FrequencyTable fq) {
		fq.clear();
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getFrequency() == 1) {
				fq.add(this.get(i).getWord(), 1);
			}
		}
	}


	/**
	 * Liefert eine String-Darstellung zur&uuml;ck.
	 *
	 * @return String-Darstellung.
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("{");
		for (int i = 0; i < this.size(); i++) {
			s.append(this.get(i).toString() + ", ");
		}
		s.append("}").append(" size = " + this.size());
		return s.toString();
	}
}
