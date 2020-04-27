package telran.measure;

public enum LengthUnit {
	MM(1f), CM(10f), IN(25.4f), FT(304.8f), M(1000f);
	private float value;

	private LengthUnit(float value) {
		this.value = value;
	}
	public float getValue() {
		return this.value;
	}
	public float between(Length length1, Length length2) {
		float number1 = length1.getNumber() * ( length1.getUnit().getValue() / this.value);
		float number2 = length2.getNumber() * (length2.getUnit().getValue() / this.value);
		return  number2 - number1;
		
	}
}
