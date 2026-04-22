public class QuantityMeasurementApp {

    // ---------------- ENUM FOR UNITS ----------------
    public enum LengthUnit {
        FEET(1.0),                 // base unit
        INCH(1.0 / 12.0),          // 1 inch = 1/12 feet
        YARDS(3.0),                // 1 yard = 3 feet
        CENTIMETERS(0.0328084);    // 1 cm ≈ 0.0328084 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // ---------------- GENERIC QUANTITY CLASS ----------------
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            // Reflexive
            if (this == obj) return true;

            // Null & type safety
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            // Compare after conversion
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("Input: Quantity(1.0, YARDS) and Quantity(3.0, FEET)");
        System.out.println("Output: Equal (" + q1.equals(q2) + ")");

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q4 = new QuantityLength(36.0, LengthUnit.INCH);

        System.out.println("Input: Quantity(1.0, YARDS) and Quantity(36.0, INCHES)");
        System.out.println("Output: Equal (" + q3.equals(q4) + ")");

        QuantityLength q5 = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength q6 = new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println("Input: Quantity(1.0, CENTIMETERS) and Quantity(0.393701, INCHES)");
        System.out.println("Output: Equal (" + q5.equals(q6) + ")");
    }
}