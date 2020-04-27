package telran.measure;

public class Length {
	private float number;
	private LengthUnit unit;

	public Length(float number, LengthUnit unit) {
		this.number = number;
		this.unit = unit;
	}
	
	/** Setters and getters ********/
	public float getNumber() {
		return this.number;
	}
	public LengthUnit getUnit() {
		return this.unit;
	}
	public void setNubmer(float number) {
		this.number = number;
	}
	public void setUnit(LengthUnit unit) {
		this.unit = unit;
	}
	
	/**  Class Functionality ********/
	public Length plus(Length length) {
		length = length.convert(this.unit);
		float newNumber = this.number + length.getNumber();
				
		return new Length(newNumber, this.unit);
	}
	public Length minus(Length length) {
		length = length.convert(this.unit);
		float newNumber = this.number - length.getNumber();
				
		return new Length(newNumber, this.unit);
	}
	public Length convert(LengthUnit unit) {
		float newNumber =  this.number * (this.unit.getValue() / unit.getValue());
		Length resLength = new Length(newNumber, unit);
		return resLength;
	}
	public String toString(){
		return this.number + this.unit.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Length other = (Length) obj;
		if (Float.floatToIntBits(number) != Float.floatToIntBits(other.number))
			return false;
		if (unit != other.unit)
			return false;
		return true;
	}
}
